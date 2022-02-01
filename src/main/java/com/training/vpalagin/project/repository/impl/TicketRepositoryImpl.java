package com.training.vpalagin.project.repository.impl;

import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Ticket> getAll(Long page) {
        int pageSize = 5;
        int startPageNumber = (int) ((pageSize * page) - 5);
        return entityManager
                .createQuery("from Ticket t", Ticket.class)
                .setFirstResult(startPageNumber)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public List<Ticket> getMyTicket(Long page, String email, String role) {
        int pageSize = 5;
        int startPageNumber = (int) ((pageSize * page) - 5);
        String query = "";
        if (role.equals("EMPLOYEE")) {
            query = "from Ticket t where t.owner.email = :email";
        }
        else if (role.equals("MANAGER")) {
            query = "from Ticket t where t.owner.email = :email or t.approver.email = :email";
        }
        else if (role.equals("ENGINEER")) {
            query = "from Ticket t where t.approver.email = :email or t.assignee.email = :email";
        }
        return entityManager
                .createQuery(query, Ticket.class)
                .setParameter("email", email)
                .setFirstResult(startPageNumber)
                .setMaxResults(pageSize)
                .getResultList();
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