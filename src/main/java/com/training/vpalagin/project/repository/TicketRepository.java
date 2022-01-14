package com.training.vpalagin.project.repository;

import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.enums.State;
import com.training.vpalagin.project.model.enums.Urgency;

import java.util.Date;
import java.util.List;

public interface TicketRepository {
    List<Ticket> getAll();

    Ticket getById(Long id);

    void add(Ticket ticket);

    void update(Ticket ticket);
}
