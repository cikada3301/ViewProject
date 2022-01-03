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
    public Ticket getId(Long id) {
        return entityManager
                .createQuery("FROM Ticket t WHERE t.id = :id", Ticket.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Ticket getByName(String keyword) {
        return entityManager
                .createQuery("FROM Ticket t WHERE t.id = :keyword or" +
                        "t.name= :keyword or " +
                        "t.date= :keyword, t.urgency= :keyword or" +
                        " t.state= :keyword", Ticket.class)
                .setParameter("keyword", keyword)
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