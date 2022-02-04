package com.training.vpalagin.project.service.impl;

import com.training.vpalagin.project.converter.*;
import com.training.vpalagin.project.dto.comment.CommentCreationDto;
import com.training.vpalagin.project.dto.comment.CommentUpdateDto;
import com.training.vpalagin.project.dto.history.HistoryDto;
import com.training.vpalagin.project.dto.ticket.TicketCreationDto;
import com.training.vpalagin.project.dto.ticket.TicketUpdateDto;
import com.training.vpalagin.project.dto.ticket.TicketViewDto;
import com.training.vpalagin.project.logger.Logger;
import com.training.vpalagin.project.model.*;
import com.training.vpalagin.project.model.enums.Action;
import com.training.vpalagin.project.model.userDetails.JwtUser;
import com.training.vpalagin.project.repository.TicketRepository;
import com.training.vpalagin.project.repository.UserRepository;
import com.training.vpalagin.project.service.*;
import com.training.vpalagin.project.service.transition.TicketTransitionMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    private final UserRepository userRepository;

    private final TicketConverter ticketConverter;

    private final TicketTransitionMap ticketTransitionMap;

    private final HistoryService historyService;

    private final HistoryConverter historyConverter;

    private final CommentService commentService;

    private final UserConverter userConverter;

    private final AttachmentService attachmentService;

    private final EmailService emailService;

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
    public List<TicketViewDto> getAll(Long page) {
        List<Ticket> ticketList = ticketRepository.getAll(page);
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
    public List<TicketViewDto> getMyTicket(Long page, JwtUser jwtUser) {
        User user = userRepository.getByEmail(jwtUser.getUsername()).get();
        List<TicketViewDto> ticketList = ticketRepository.getMyTicket(page, user.getEmail(), user.getRole().toString()).stream()
                .map(ticketConverter::convertToViewDto)
                .sorted(Comparator.comparing(TicketViewDto::getUrgency)
                        .thenComparing(TicketViewDto::getDesiredResolutionDate))
                .collect(Collectors.toList());
        for (TicketViewDto ticket : ticketList) {
            ticket.setActionList(ticketTransitionMap.getActions(ticket, user));
        }
        return ticketList;
    }

    @Override
    @Logger
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<TicketViewDto> getById(Long id) {
        Ticket ticket = ticketRepository.getById(id);
        return Optional.of(ticketConverter.convertToViewDto(ticket));
    }

    @Override
    @Logger
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public List<Ticket> sort(String param, Long page) {
        return ticketRepository.getAll(page).stream()
                .sorted(mapSort.get(param))
                .collect(Collectors.toList());
    }

    @Override
    @Logger
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public List<TicketViewDto> find(String param, Long page) {
        List<Ticket> tickets = ticketRepository.getAll(page).stream()
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
    public void add(TicketCreationDto ticketCreationDto) throws IOException {
        Ticket ticket = ticketConverter.convertFromCreationDto(ticketCreationDto);
        ticketRepository.add(ticket);
        User user = userConverter.convertFromViewDto(ticketCreationDto.getOwner());
        History history = History.builder()
                .ticket(ticket)
                .date(ticket.getDesiredResolutionDate())
                .action(Action.CREATE)
                .user(user)
                .description(ticket.getDescription())
                .build();
        historyService.add(history);
        Attachment attachment = Attachment.builder()
                .file(ticketCreationDto.getFile().getBytes())
                .name(ticketCreationDto.getFile().getName())
                .ticket(ticket)
                .build();
        attachmentService.add(attachment);
        if (!ticketCreationDto.getComment().isEmpty()) {
            CommentCreationDto comment = CommentCreationDto.builder()
                    .date(ticket.getCreatedOn())
                    .text(ticketCreationDto.getComment())
                    .user(ticketCreationDto.getOwner())
                    .ticket(ticketConverter.convertToCreationDto(ticket))
                    .build();
            commentService.add(comment);
        }
    }

    @Override
    @Logger
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void edit(Long id, TicketUpdateDto ticketUpdateDto) throws IOException {
        Ticket ticketForEdit = ticketRepository.getById(id);
        Ticket ticket = ticketConverter.convertFromUpdateDto(ticketUpdateDto);
        ticketForEdit.setName(ticket.getName());
        ticketForEdit.setDescription(ticket.getDescription());
        ticketForEdit.setCreatedOn(ticket.getCreatedOn());
        ticketForEdit.setDesiredResolutionDate(ticket.getDesiredResolutionDate());
        ticketForEdit.setAssignee(ticket.getAssignee());
        ticketForEdit.setOwner(ticket.getOwner());
        ticketForEdit.setState(ticket.getState());
        ticketForEdit.setUrgency(ticket.getUrgency());
        ticketForEdit.setCategory(ticket.getCategory());
        ticketForEdit.setApprover(ticket.getApprover());
        ticketRepository.update(ticketForEdit);
        History history = historyConverter.convertFromDto(HistoryDto.builder()
                .ticket(ticketConverter.convertToViewDto(ticketForEdit))
                .dateAction(ticketForEdit.getCreatedOn())
                .action(Action.CREATE)
                .user(ticketUpdateDto.getOwner())
                .description(ticketForEdit.getDescription())
                .build());
        historyService.add(history);
        if (!ticketUpdateDto.getComment().isEmpty()) {
            CommentUpdateDto comment = CommentUpdateDto.builder()
                    .date(ticket.getCreatedOn())
                    .text(ticketUpdateDto.getComment())
                    .user(ticketUpdateDto.getOwner())
                    .ticket(ticketUpdateDto)
                    .build();
            commentService.add(comment);
        }
        Attachment attachment = Attachment.builder()
                .file(ticketUpdateDto.getFile().getBytes())
                .name(ticketUpdateDto.getFile().getName())
                .ticket(ticket)
                .build();
        attachmentService.add(attachment);
    }

    @Override
    @Logger
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void transitStatus(Long id, Action action, JwtUser jwtUser) throws MessagingException {
        User user = userRepository.getByEmail("user1_mogilev@yopmail.com").get();
        Ticket ticket = ticketRepository.getById(id);
        emailService.sendMessage(ticket, action);
        ticket.setState(ticketTransitionMap.getTransientState(ticket, user, action));
        ticketRepository.update(ticket);
        History history = historyConverter.convertFromDto(HistoryDto.builder()
                .ticket(ticketConverter.convertToViewDto(ticket))
                .dateAction(ticket.getCreatedOn())
                .action(action)
                .user(userConverter.convertToViewDto(user))
                .description(ticket.getDescription())
                .build());
        historyService.add(history);
    }
}