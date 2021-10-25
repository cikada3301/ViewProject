package com.training.vpalagin.project.service;

import com.training.vpalagin.project.dto.TicketDto;
import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.enums.State;
import com.training.vpalagin.project.model.enums.Urgency;

import java.util.Date;
import java.util.List;

public interface TicketService {
    List<TicketDto> getAll();

    List<Ticket> getSortedAscendingTicketsById();

    List<Ticket> getSortedDescendingTicketsById();

    List<Ticket> getSortedAlphabeticOrderTicketsByName();

    List<Ticket> getSortedInvertedOrderTicketsByName();

    List<Ticket> getSortedAscendingTicketsByDate();

    List<Ticket> getSortedDescendingTicketsByDate();

    List<Ticket> getSortedTicketsByUrgency();

    List<Ticket> getSortedInvertedTicketsByUrgency();

    List<Ticket> getSortedAlphabeticOrderTicketsByStatus();

    List<Ticket> getSortedInvertedTicketsByStatus();

    List<Ticket> getTicketById(Long id);

    List<Ticket> getTicketByName(String name);

    List<Ticket> getTicketByDate(Date date);

    List<Ticket> getTicketByUrgency(Urgency urgency);

    List<Ticket> getTicketByState(State state);

    void addTicket(Ticket ticket);

    void editTicket(Long id, Ticket ticket);
}
