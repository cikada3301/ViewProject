package com.training.vpalagin.project.service.impl;

import com.training.vpalagin.project.converter.AttachmentConverter;
import com.training.vpalagin.project.converter.CommentConverter;
import com.training.vpalagin.project.converter.HistoryConverter;
import com.training.vpalagin.project.converter.TicketConverter;
import com.training.vpalagin.project.converter.UserConverter;
import com.training.vpalagin.project.dto.comment.CommentMutationDto;
import com.training.vpalagin.project.dto.ticket.TicketCreationDto;
import com.training.vpalagin.project.dto.ticket.TicketUpdateDto;
import com.training.vpalagin.project.dto.ticket.TicketViewDto;
import com.training.vpalagin.project.exception.NotAuthorizedException;
import com.training.vpalagin.project.exception.TicketTransitStateException;
import com.training.vpalagin.project.logger.Logger;
import com.training.vpalagin.project.model.Attachment;
import com.training.vpalagin.project.model.History;
import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.User;
import com.training.vpalagin.project.model.enums.Action;
import com.training.vpalagin.project.model.enums.UserRole;
import com.training.vpalagin.project.repository.TicketRepository;
import com.training.vpalagin.project.repository.UserRepository;
import com.training.vpalagin.project.security.userDetails.JwtUser;
import com.training.vpalagin.project.service.AttachmentService;
import com.training.vpalagin.project.service.CommentService;
import com.training.vpalagin.project.service.EmailService;
import com.training.vpalagin.project.service.HistoryService;
import com.training.vpalagin.project.service.TicketService;
import com.training.vpalagin.project.service.transition.TicketTransitionMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    public static final String NO_ACCESS_FOR_ROLE_ENGINEER = "No access for role engineer";

    private final TicketRepository ticketRepository;

    private final UserRepository userRepository;

    private final HistoryService historyService;

    private final CommentService commentService;

    private final AttachmentService attachmentService;

    private final EmailService emailService;

    private final TicketConverter ticketConverter;

    private final AttachmentConverter attachmentConverter;

    private final UserConverter userConverter;

    private final HistoryConverter historyConverter;

    private final CommentConverter commentConverter;

    private final TicketTransitionMap ticketTransitionMap;

    private final Map<String, Comparator<Ticket>> mapSort = Map.of(
            "id", Comparator.comparing(Ticket::getId),
            "name", Comparator.comparing(Ticket::getName),
            "date", Comparator.comparing(Ticket::getDesiredResolutionDate),
            "urgency", Comparator.comparing(Ticket::getUrgency),
            "status", Comparator.comparing(Ticket::getState)
    );

    @Override
    @Logger
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public List<TicketViewDto> getAll(Long page, JwtUser jwtUser) {
        User user = userRepository.getByEmail(jwtUser.getUsername()).get();
        List<Ticket> ticketList = ticketRepository.getAll(page, user.getEmail(), user.getRole().toString());
        return ticketList
                .stream()
                .map(ticketConverter::convertToViewDto)
                .sorted(Comparator.comparing(TicketViewDto::getUrgency)
                        .thenComparing(TicketViewDto::getDesiredResolutionDate))
                .collect(Collectors.toList());
    }

    @Override
    @Logger
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public List<TicketViewDto> getMy(Long page, JwtUser jwtUser) {
        User user = userRepository.getByEmail(jwtUser.getUsername()).get();
        List<TicketViewDto> ticketList = ticketRepository.getMy(page, user.getEmail(), user.getRole().toString()).stream()
                .map(ticketConverter::convertToViewDto)
                .sorted(Comparator.comparing(TicketViewDto::getUrgency)
                        .thenComparing(TicketViewDto::getDesiredResolutionDate))
                .collect(Collectors.toList());
        ticketList.forEach(ticket -> ticket.setActionList(ticketTransitionMap.getActions(ticket, user)));
        return ticketList;
    }

    @Override
    @Logger
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public TicketViewDto getByTicketId(Long id) {
        Ticket ticket = ticketRepository.getById(id);
        return ticketConverter.convertToViewDto(ticket);
    }

    @Override
    @Logger
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public List<Ticket> sort(String param, Long page, JwtUser jwtUser) {
        User user = userRepository.getByEmail(jwtUser.getUsername()).get();
        return ticketRepository.getAll(page, user.getEmail(), user.getRole().toString()).stream()
                .sorted(mapSort.get(param))
                .collect(Collectors.toList());
    }

    @Override
    @Logger
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public List<TicketViewDto> find(String param, Long page, JwtUser jwtUser) {
        User user = userRepository.getByEmail(jwtUser.getUsername()).get();
        List<Ticket> tickets = ticketRepository.getAll(page, user.getEmail(), user.getRole().toString()).stream()
                .filter(ticket -> ticket.getId().toString().contains(param) ||
                        ticket.getName().contains(param) ||
                        ticket.getDesiredResolutionDate().toString().contains(param) ||
                        ticket.getUrgency().toString().contains(param) ||
                        ticket.getState().toString().contains(param)).collect(Collectors.toList());

        return tickets.stream().map(ticketConverter::convertToViewDto).collect(Collectors.toList());
    }

    @Override
    @Logger
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void add(TicketCreationDto ticketCreationDto, JwtUser jwtUser) throws IOException {
        if (jwtUser.getUser().getRole().equals(UserRole.ENGINEER)) {
            throw new NotAuthorizedException(NO_ACCESS_FOR_ROLE_ENGINEER);
        }
        Ticket ticket = ticketConverter.convertFromCreationDto(ticketCreationDto);
        ticketRepository.add(ticket);
        User user = jwtUser.getUser();
        ticket.setOwner(user);
        History history = historyConverter.convertHistory(ticket, user);
        historyService.add(history);
        if (Objects.nonNull(ticketCreationDto.getFile())) {
            Attachment attachment = attachmentConverter.convertAttachment(ticket, ticketCreationDto.getFile());
            historyService.add(historyConverter.convertToHistoryWithAttachment(ticket, user));
            attachmentService.add(attachment);
        }
        if (!ticketCreationDto.getComment().isEmpty()) {
            CommentMutationDto comment = commentConverter.convertMutationDto(ticket, ticketCreationDto.getComment(), ticketCreationDto.getOwner());
            commentService.add(comment);
        }
    }

    @Override
    @Logger
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void edit(Long id, TicketUpdateDto ticketUpdateDto, JwtUser jwtUser) throws IOException {
        if (jwtUser.getUser().getRole().equals(UserRole.ENGINEER)) {
            throw new NotAuthorizedException("No access for role engineer");
        }
        User user = jwtUser.getUser();
        Ticket ticket = ticketConverter.convertFromUpdateDto(ticketUpdateDto);
        ticket.setOwner(user);
        Ticket ticketForEdit = ticketRepository.getById(id);
        ticketConverter.convertForEditTicket(ticket, ticketForEdit);
        ticketRepository.update(ticketForEdit);
        History history = historyConverter.convertHistory(ticketForEdit, Action.CREATE, ticketUpdateDto.getOwner());
        historyService.add(history);
        if (!ticketUpdateDto.getComment().isEmpty()) {
            CommentMutationDto comment = commentConverter.convertMutationDto(ticket, ticketUpdateDto.getComment(), ticketUpdateDto.getOwner());
            commentService.add(comment);
        }
        if (Objects.nonNull(ticketUpdateDto.getFile())) {
            Attachment attachment = attachmentConverter.convertAttachment(ticket, ticketUpdateDto.getFile());
            attachmentService.add(attachment);
            historyService.add(historyConverter.convertToHistoryWithAttachmentEdit(ticket, user));
        }
    }

    @Override
    @Logger
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void transitStatus(Long id, Action action, JwtUser jwtUser) throws MessagingException {
        User user = userRepository.getByEmail(jwtUser.getUsername()).get();
        Ticket ticket = ticketRepository.getById(id);
        emailService.sendMessage(ticket, action);
        if (action == Action.ASSIGN_TO_ME) {
            ticket.setAssignee(jwtUser.getUser());
        } else if (action == Action.APPROVE) {
            ticket.setApprover(jwtUser.getUser());
        }
        if (ticketTransitionMap.getActions(ticketConverter.convertToViewDto(ticket), user).isEmpty()) {
            throw new TicketTransitStateException("This state is not allowed");
        }
        ticket.setState(ticketTransitionMap.getTransientState(ticket, user, action));
        ticketRepository.update(ticket);
        History history = historyConverter.convertHistory(ticket, action, userConverter.convertToViewDto(user));
        historyService.add(history);
    }
}
