package com.training.vpalagin.project.repository.impl;

import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.enums.State;
import com.training.vpalagin.project.model.enums.UserRole;
import com.training.vpalagin.project.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {

    private static final String FROM_TICKET_T = "from Ticket t";

    private static final int PAGE_SIZE = 5;

    private static final String QUERY_FOR_EMPLOYEE = FROM_TICKET_T + " where t.owner.email =: email";

    private static final String QUERY_FOR_MANAGER = FROM_TICKET_T + " where t.owner.email =: email" +
            " or t.owner.role = '" + UserRole.EMPLOYEE + "'" +
            " and t.state = '" + State.NEW + "'" +
            " or t.approver.email := " +
            " and t.state in ('" + State.APPROVED + "','" + State.DECLINED + "','" + State.CANCELED + "','" + State.IN_PROGRESS + "','" + State.DONE + "')";

    private static final String QUERY_FOR_ENGINEER = FROM_TICKET_T +
            " where t.owner.role in ('" + UserRole.EMPLOYEE + "','" + UserRole.MANAGER + "')" +
            " and t.state = '" + State.APPROVED + "'" +
            " or t.assignee.email =: email" +
            " and t.state in ('" + State.IN_PROGRESS + "','" + State.DONE + "')";

    private static final Map<String, String> MAP_OF_QUERY_ALL_TICKETS = Map.of(
            "EMPLOYEE", QUERY_FOR_EMPLOYEE,
            "MANAGER", QUERY_FOR_MANAGER,
            "ENGINEER", QUERY_FOR_ENGINEER
    );

    private static final Map<String, String> MAP_OF_QUERY_MY_TICKETS = Map.of(
            "EMPLOYEE", "from Ticket t where t.owner.email = :email",
            "MANAGER", "from Ticket t where t.owner.email = :email or t.approver.email = :email",
            "ENGINEER", "from Ticket t where t.approver.email = :email or t.assignee.email = :email"
    );

    private static final String FROM_TICKET_T_WHERE_T_ID_ID = "FROM Ticket t WHERE t.id = :id";

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Ticket> getAll(Long page, String email, String role) {
        int startPageNumber = (int) ((PAGE_SIZE * page) - 5);
        return entityManager
                .createQuery(MAP_OF_QUERY_ALL_TICKETS.get(role), Ticket.class)
                .setParameter("email", email)
                .setFirstResult(startPageNumber)
                .setMaxResults(PAGE_SIZE)
                .getResultList();
    }

    @Override
    public List<Ticket> getMy(Long page, String email, String role) {
        int pageSize = 5;
        int startPageNumber = (int) ((pageSize * page) - 5);
        return entityManager
                .createQuery(MAP_OF_QUERY_MY_TICKETS.get(role), Ticket.class)
                .setParameter("email", email)
                .setFirstResult(startPageNumber)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public Ticket getById(Long id) {
        return entityManager
                .createQuery(FROM_TICKET_T_WHERE_T_ID_ID, Ticket.class)
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