package com.training.vpalagin.project.service.impl;

import com.training.vpalagin.project.converter.HistoryConverter;
import com.training.vpalagin.project.converter.TicketConverter;
import com.training.vpalagin.project.dto.HistoryDto;
import com.training.vpalagin.project.dto.TicketDto;
import com.training.vpalagin.project.logger.TicketLogger;
import com.training.vpalagin.project.model.History;
import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.User;
import com.training.vpalagin.project.model.enums.Action;
import com.training.vpalagin.project.repository.HistoryRepository;
import com.training.vpalagin.project.repository.TicketRepository;
import com.training.vpalagin.project.repository.UserRepository;
import com.training.vpalagin.project.service.TicketService;
import com.training.vpalagin.project.service.transition.TicketTransitionMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    private final UserRepository userRepository;

    private final TicketConverter ticketConverter;

    private final TicketTransitionMap ticketTransitionMap;

    private final HistoryRepository historyRepository;

    private final HistoryConverter historyConverter;

    private final Map<String, Comparator<Ticket>> mapSort = new HashMap<>() {{
        put("id", Comparator.comparing(Ticket::getId));
        put("name", Comparator.comparing(Ticket::getName));
        put("date", Comparator.comparing(Ticket::getDesiredResolutionDate));
        put("urgency", Comparator.comparing(Ticket::getUrgency));
        put("status", Comparator.comparing(Ticket::getState));
    }};

    @Override
    @TicketLogger
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public List<TicketDto> getAll() {
        List<Ticket> ticketList = ticketRepository.getAll();
        return ticketList
                .stream()
                .map(ticketConverter::convertToDto)
                .sorted(Comparator.comparing(TicketDto::getUrgencyId)
                        .thenComparing(TicketDto::getDesiredResolutionDate))
                .collect(Collectors.toList());
    }

    @Override
    @TicketLogger
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<TicketDto>  getById(Long id) {
        Ticket ticket = ticketRepository.getId(id);
        return Optional.of(ticketConverter.convertToDto(ticket));
    }

    @Override
    @TicketLogger
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Ticket> sort(String param) {
        return ticketRepository.getAll().stream()
                .sorted(mapSort.get(param))
                .collect(Collectors.toList());
    }

    @Override
    @TicketLogger
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<Ticket> find(String param) {
        List<Ticket> tickets = ticketRepository.getAll();
        return tickets.stream().filter(ticket -> ticket.getId().toString().contains(param) ||
                ticket.getName().contains(param) ||
                ticket.getDesiredResolutionDate().toString().contains(param) ||
                ticket.getUrgency().toString().contains(param) ||
                ticket.getState().toString().contains(param)).findAny();
    }

    @Override
    @TicketLogger
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void addTicket(TicketDto ticketDto) {
        Ticket ticket = ticketConverter.convertFromDto(ticketDto);
        ticketRepository.add(ticket);
        History history = historyConverter.convertFromDto(HistoryDto.builder()
                .ticket(ticket)
                .dateAction(ticket.getDesiredResolutionDate())
                .action(Action.CREATE)
                .user(ticket.getOwner())
                .description(ticket.getDescription())
                .build());
        historyRepository.add(history);
    }

    @Override
    @TicketLogger
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void editTicket(Long id, TicketDto ticketDto) {
        Ticket ticketForEdit = ticketRepository.getId(id);
        Ticket ticket = ticketConverter.convertFromDto(ticketDto);
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
                .ticket(ticketForEdit)
                .dateAction(ticketForEdit.getDesiredResolutionDate())
                .action(Action.CREATE)
                .user(ticketForEdit.getOwner())
                .description(ticketForEdit.getDescription())
                .build());
        historyRepository.add(history);
    }

    @Override
    @TicketLogger
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void transitStatus(Long id, Action action) {
        User user = userRepository.getByEmail("vlad@gmail.com").get();
        Ticket ticket = ticketRepository.getId(id);
        Runnable runnable = ticketTransitionMap.getTransientState(ticket, user, action);
        runnable.run();
        ticketRepository.update(ticket);
        History history = historyConverter.convertFromDto(HistoryDto.builder()
                .ticket(ticket)
                .dateAction(ticket.getDesiredResolutionDate())
                .action(action)
                .user(user)
                .description(ticket.getDescription())
                .build());
        historyRepository.add(history);
    }
}