package com.training.vpalagin.project.service.impl;

import com.training.vpalagin.project.converter.TicketConverter;
import com.training.vpalagin.project.dto.TicketDto;
import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.enums.State;
import com.training.vpalagin.project.model.enums.Urgency;
import com.training.vpalagin.project.repository.TicketRepository;
import com.training.vpalagin.project.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    private final TicketConverter ticketConverter;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            readOnly = true,
            propagation = Propagation.SUPPORTS
    )
    public List<TicketDto> getAll() {
        List<Ticket> ticketList = ticketRepository.getAll();
        log.info("service tickets: {}", ticketList);
        List<TicketDto> tickets = ticketList
                .stream()
                .map(ticketConverter::convertToDto)
                .sorted(Comparator.comparing(TicketDto::getUrgencyId)
                        .thenComparing(TicketDto::getDesiredResolutionDate))
                .collect(Collectors.toList());
        log.info("tickets " + tickets);
        return tickets;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            readOnly = true,
            propagation = Propagation.SUPPORTS)
    public List<Ticket> getSortedAscendingTicketsById() {
        List<Ticket> tickets = ticketRepository.getAll().stream()
                .sorted(Comparator.comparing(Ticket::getId))
                .collect(Collectors.toList());
        log.info("tickets " + tickets);
        return tickets;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            readOnly = true,
            propagation = Propagation.SUPPORTS)
    public List<Ticket> getSortedDescendingTicketsById() {
        List<Ticket> tickets = ticketRepository.getAll().stream()
                .sorted(Comparator.comparing(Ticket::getId).reversed())
                .collect(Collectors.toList());
        log.info("tickets " + tickets);
        return tickets;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            readOnly = true,
            propagation = Propagation.SUPPORTS)
    public List<Ticket> getSortedAlphabeticOrderTicketsByName() {
        List<Ticket> tickets = ticketRepository.getAll().stream()
                .sorted(Comparator.comparing(Ticket::getName))
                .collect(Collectors.toList());
        log.info("tickets " + tickets);
        return tickets;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            readOnly = true,
            propagation = Propagation.SUPPORTS)
    public List<Ticket> getSortedInvertedOrderTicketsByName() {
        List<Ticket> tickets = ticketRepository.getAll().stream()
                .sorted(Comparator.comparing(Ticket::getName).reversed())
                .collect(Collectors.toList());
        log.info("tickets " + tickets);
        return tickets;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            readOnly = true,
            propagation = Propagation.SUPPORTS)
    public List<Ticket> getSortedAscendingTicketsByDate() {
        List<Ticket> tickets = ticketRepository.getAll().stream()
                .sorted(Comparator.comparing(Ticket::getDesiredResolutionDate))
                .collect(Collectors.toList());
        log.info("tickets " + tickets);
        return tickets;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            readOnly = true,
            propagation = Propagation.SUPPORTS)
    public List<Ticket> getSortedDescendingTicketsByDate() {
        List<Ticket> tickets = ticketRepository.getAll().stream()
                .sorted(Comparator.comparing(Ticket::getDesiredResolutionDate).reversed())
                .collect(Collectors.toList());
        log.info("tickets " + tickets);
        return tickets;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            readOnly = true,
            propagation = Propagation.SUPPORTS)
    public List<Ticket> getSortedTicketsByUrgency() {
        List<Ticket> tickets = ticketRepository.getAll().stream()
                .sorted(Comparator.comparing(Ticket::getUrgency))
                .collect(Collectors.toList());
        log.info("tickets " + tickets);
        return tickets;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            readOnly = true,
            propagation = Propagation.SUPPORTS)
    public List<Ticket> getSortedInvertedTicketsByUrgency() {
        List<Ticket> tickets = ticketRepository.getAll().stream()
                .sorted(Comparator.comparing(Ticket::getUrgency).reversed())
                .collect(Collectors.toList());
        log.info("tickets " + tickets);
        return tickets;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            readOnly = true,
            propagation = Propagation.SUPPORTS)
    public List<Ticket> getSortedAlphabeticOrderTicketsByStatus() {
        List<Ticket> tickets = ticketRepository.getAll().stream()
                .sorted(Comparator.comparing(Ticket::getState))
                .collect(Collectors.toList());
        log.info("tickets " + tickets);
        return tickets;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            readOnly = true,
            propagation = Propagation.SUPPORTS)
    public List<Ticket> getSortedInvertedTicketsByStatus() {
        List<Ticket> tickets = ticketRepository.getAll().stream()
                .sorted(Comparator.comparing(Ticket::getState).reversed())
                .collect(Collectors.toList());
        log.info("tickets " + tickets);
        return tickets;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            readOnly = true,
            propagation = Propagation.SUPPORTS)
    public List<Ticket> getTicketById(Long id) {
        List<Ticket> ticket = ticketRepository.getTicketById(id);
        log.info("tickets " + ticket);
        return ticket;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            readOnly = true,
            propagation = Propagation.SUPPORTS)
    public List<Ticket> getTicketByName(String name) {
        List<Ticket> ticket = ticketRepository.getTicketByName(name.toLowerCase());
        log.info("tickets " + ticket);
        return ticket;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            readOnly = true,
            propagation = Propagation.SUPPORTS)
    public List<Ticket> getTicketByDate(Date date) {
        List<Ticket> ticket = ticketRepository.getTicketByDate(date);
        log.info("tickets " + ticket);
        return ticket;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            readOnly = true,
            propagation = Propagation.SUPPORTS)
    public List<Ticket> getTicketByUrgency(Urgency urgency) {
        List<Ticket> ticket = ticketRepository.getTicketByUrgency(urgency);
        log.info("tickets " + ticket);
        return ticket;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            readOnly = true,
            propagation = Propagation.SUPPORTS)
    public List<Ticket> getTicketByState(State state) {
        List<Ticket> ticket = ticketRepository.getTicketByState(state);
        log.info("tickets " + ticket);
        return ticket;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED)
    public void addTicket(Ticket ticket) {
        ticketRepository.addTicket(ticket);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED
    )
    public void editTicket(Long id, Ticket ticket) {
        Ticket ticketForEdit = ticketRepository.findTicketById(id);
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
        ticketRepository.editTicket(ticketForEdit);
    }
}
