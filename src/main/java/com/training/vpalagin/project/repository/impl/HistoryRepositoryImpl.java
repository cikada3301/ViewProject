package com.training.vpalagin.project.repository.impl;

import com.training.vpalagin.project.model.History;
import com.training.vpalagin.project.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class HistoryRepositoryImpl implements HistoryRepository {

    private final EntityManager entityManager;

    @Override
    public List<History> get(Long id) {
        return entityManager.createQuery("from History h where h.ticket.id = :id", History.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public void add(History history) {
        entityManager.persist(history);
    }
}