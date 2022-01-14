package com.training.vpalagin.project.repository.impl;

import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {

    private final EntityManager entityManager;

    @Override
    public List<Ticket> getAll() {
        List<Ticket> tickets = entityManager
                .createQuery("from Ticket t", Ticket.class)
                .getResultList();
        return tickets;
    }

    @Override
    public Ticket getById(Long id) {
        return entityManager
                .createQuery("FROM Ticket t WHERE t.id = :id", Ticket.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void add(Ticket ticket) {
        entityManager.persist(ticket);
    }

    @Override
    public void update(Ticket ticket) {
        entityManager.merge(ticket);
    }
}