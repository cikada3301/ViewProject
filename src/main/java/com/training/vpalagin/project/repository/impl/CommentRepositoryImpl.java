package com.training.vpalagin.project.repository.impl;

import com.training.vpalagin.project.model.Comment;
import com.training.vpalagin.project.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Comment> get(Long id) {
        return entityManager.createQuery("from Comment c where c.ticket.id = :id", Comment.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public void add(Comment comment) {
        entityManager.persist(comment);
    }
}
