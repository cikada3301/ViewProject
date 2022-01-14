package com.training.vpalagin.project.service.impl;

import com.training.vpalagin.project.converter.*;
import com.training.vpalagin.project.dto.*;
import com.training.vpalagin.project.logger.Logger;
import com.training.vpalagin.project.model.*;
import com.training.vpalagin.project.model.enums.Action;
import com.training.vpalagin.project.repository.TicketRepository;
import com.training.vpalagin.project.repository.UserRepository;
import com.training.vpalagin.project.service.AttachmentService;
import com.training.vpalagin.project.service.CommentService;
import com.training.vpalagin.project.service.HistoryService;
import com.training.vpalagin.project.service.TicketService;
import com.training.vpalagin.project.service.transition.TicketTransitionMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    private final CommentConverter commentConverter;

    private final AttachmentConverter attachmentConverter;

    private final AttachmentService attachmentService;

    private final Map<String, Comparator<Ticket>> mapSort = new HashMap<>() {{
        put("id", Comparator.comparing(Ticket::getId));
        put("name", Comparator.comparing(Ticket::getName));
        put("date", Comparator.comparing(Ticket::getDesiredResolutionDate));
        put("urgency", Comparator.comparing(Ticket::getUrgency));
        put("status", Comparator.comparing(Ticket::getState));
    }};

    @Override
    @Logger
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public List<TicketViewDto> getAll() {
        List<Ticket> ticketList = ticketRepository.getAll();
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
    public Optional<TicketViewDto> getById(Long id) {
        Ticket ticket = ticketRepository.getById(id);
        return Optional.of(ticketConverter.convertToViewDto(ticket));
    }

    @Override
    @Logger
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Ticket> sort(String param) {
        return ticketRepository.getAll().stream()
                .sorted(mapSort.get(param))
                .collect(Collectors.toList());
    }

    @Override
    @Logger
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public List<TicketViewDto> find(String param) {
        List<TicketViewDto> tickets = ticketRepository.getAll().stream().map(ticketConverter::convertToViewDto).collect(Collectors.toList());
        return tickets.stream().filter(ticket -> ticket.getId().toString().contains(param) ||
                ticket.getName().contains(param) ||
                ticket.getDesiredResolutionDate().toString().contains(param) ||
                ticket.getUrgency().toString().contains(param) ||
                ticket.getState().toString().contains(param)).collect(Collectors.toList());
    }

    @Override
    @Logger
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void add(TicketCreationDto ticketCreationDto) {
        Ticket ticket = ticketConverter.convertFromCreationDto(ticketCreationDto);
        ticketRepository.add(ticket);
        User user = userConverter.convertFromViewDto(ticketCreationDto.getOwner());
        History history = History.builder()
                .ticket(ticket)
                .dateAction(ticket.getDesiredResolutionDate())
                .action(Action.CREATE)
                .user(user)
                .description(ticket.getDescription())
                .build();
        historyService.add(history);
        if (!ticketCreationDto.getComment().equals("")) {
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
    public void edit(Long id, TicketUpdateDto ticketUpdateDto) {
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
                .dateAction(ticketForEdit.getDesiredResolutionDate())
                .action(Action.CREATE)
                .user(ticketUpdateDto.getOwner())
                .description(ticketForEdit.getDescription())
                .build());
        historyService.add(history);
        if (!ticketUpdateDto.getComment().equals("")) {
            CommentUpdateDto comment = CommentUpdateDto.builder()
                    .date(ticket.getCreatedOn())
                    .text(ticketUpdateDto.getComment())
                    .user(ticketUpdateDto.getOwner())
                    .ticket(ticketUpdateDto)
                    .build();
            commentService.add(comment);
        }
    }

    @Override
    @Logger
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void transitStatus(Long id, Action action) {
        User user = userRepository.getByEmail("user1_mogilev@yopmail.com").get();
        Ticket ticket = ticketRepository.getById(id);
        Runnable runnable = ticketTransitionMap.getTransientState(ticket, user, action);
        runnable.run();
        ticketRepository.update(ticket);
        History history = historyConverter.convertFromDto(HistoryDto.builder()
                .ticket(ticketConverter.convertToViewDto(ticket))
                .dateAction(ticket.getDesiredResolutionDate())
                .action(action)
                .user(userConverter.convertToViewDto(user))
                .description(ticket.getDescription())
                .build());
        historyService.add(history);
    }
}