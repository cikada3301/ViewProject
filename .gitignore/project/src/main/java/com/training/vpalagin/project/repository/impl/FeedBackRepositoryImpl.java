package com.training.vpalagin.project.repository.impl;

import com.training.vpalagin.project.model.Feedback;
import com.training.vpalagin.project.repository.FeedBackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class FeedBackRepositoryImpl implements FeedBackRepository {

    private final EntityManager entityManager;

    @Override
    public void add(Feedback feedBack) {
        entityManager.merge(feedBack);
    }
}
