package com.training.vpalagin.project.repository;

import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.enums.State;
import com.training.vpalagin.project.model.enums.Urgency;

import java.util.Date;
import java.util.List;

public interface TicketRepository {
    List<Ticket> getAll();

    Ticket findTicketById(Long id);

    List<Ticket> getTicketById(Long id);

    List<Ticket> getTicketByName(String name);

    List<Ticket> getTicketByDate(Date date);

    List<Ticket> getTicketByUrgency(Urgency urgency);

    List<Ticket> getTicketByState(State state);

    void addTicket(Ticket ticket);

    void editTicket(Ticket ticket);
}
