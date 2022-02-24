package com.training.vpalagin.project.repository.impl;

import com.training.vpalagin.project.model.History;
import com.training.vpalagin.project.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class HistoryRepositoryImpl implements HistoryRepository {

    private static final String FROM_HISTORY_H_WHERE_H_TICKET_ID_ID = "from History h where h.ticket.id = :id";

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<History> getAllByTicketId(Long id) {
        return entityManager.createQuery(FROM_HISTORY_H_WHERE_H_TICKET_ID_ID, History.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public void add(History history) {
        entityManager.persist(history);
    }
}