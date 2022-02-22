package com.training.vpalagin.project.repository;

import com.training.vpalagin.project.model.Ticket;

import java.util.List;

public interface TicketRepository {
    List<Ticket> getAll(Long page, String email, String role);

    List<Ticket> getMy(Long page, String email, String role);

    Ticket getById(Long id);

    void add(Ticket ticket);

    void update(Ticket ticket);
}
