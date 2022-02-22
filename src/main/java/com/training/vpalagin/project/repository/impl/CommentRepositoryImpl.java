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

    private static final String FROM_COMMENT_C_WHERE_C_TICKET_ID_ID = "from Comment c where c.ticket.id = :id";

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Comment> getAllByTicketId(Long id) {
        return entityManager.createQuery(FROM_COMMENT_C_WHERE_C_TICKET_ID_ID, Comment.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public void add(Comment comment) {
        entityManager.persist(comment);
    }
}
