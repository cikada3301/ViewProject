package com.training.vpalagin.project.repository.impl;

import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.enums.State;
import com.training.vpalagin.project.model.enums.Urgency;
import com.training.vpalagin.project.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {

    private final EntityManager entityManager;

    @Override
    public List<Ticket> getAll() {
        List<Ticket> tickets = entityManager
                .createQuery("from Ticket t left join fetch t.category" +
                        " left join fetch t.assignee" +
                        " left join fetch t.owner" +
                        " left join fetch t.approver", Ticket.class)
                .getResultList();
        log.info("Get all tickets: {}", tickets.toString());
        return tickets;
    }

    @Override
    public Ticket findTicketById(Long id) {
        final Ticket ticket = entityManager
                .createQuery("from Ticket t where t.id = :id", Ticket.class)
                .setParameter("id", id)
                .getSingleResult();
        return ticket;
    }

    @Override
    public List<Ticket> getTicketById(Long id) {
        final List<Ticket> ticket = entityManager
                .createQuery("from Ticket t where t.id = :id", Ticket.class)
                .setParameter("id", id)
                .getResultList();
        return ticket;
    }

    @Override
    public List<Ticket> getTicketByName(String name) {
        final List<Ticket> ticket = entityManager
                .createQuery("from Ticket t where t.name = :name", Ticket.class)
                .setParameter("name", name.toLowerCase())
                .getResultList();
        return ticket;
    }

    @Override
    public List<Ticket> getTicketByDate(Date date) {
        final List<Ticket> ticket = entityManager
                .createQuery("from Ticket t where t.desired_resolution_date = :desired_resolution_date", Ticket.class)
                .setParameter("desired_resolution_date", date)
                .getResultList();
        return ticket;
    }

    @Override
    public List<Ticket> getTicketByUrgency(Urgency urgency) {
        final List<Ticket> ticket = entityManager
                .createQuery("from Ticket t where t.urgency = :urgency", Ticket.class)
                .setParameter("urgency", urgency)
                .getResultList();
        return ticket;
    }

    @Override
    public List<Ticket> getTicketByState(State state) {
        final List<Ticket> ticket = entityManager
                .createQuery("from Ticket t where t.state = :state", Ticket.class)
                .setParameter("state", state)
                .getResultList();
        return ticket;
    }

    @Override
    public void addTicket(Ticket ticket) {
        entityManager.persist(ticket);
    }

    @Override
    public void editTicket(Ticket ticket) {
        entityManager.merge(ticket);
    }
}
