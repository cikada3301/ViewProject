package com.training.vpalagin.project.repository.impl;

import com.training.vpalagin.project.model.Attachment;
import com.training.vpalagin.project.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AttachmentRepositoryImpl implements AttachmentRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Attachment> getAll() {
        return entityManager
                .createQuery("from Attachment a ", Attachment.class)
                .getResultList();
    }

    @Override
    public Attachment getById(Long id) {
        return entityManager
                .createQuery("from Attachment a where a.ticket.id = :id", Attachment.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void add(Attachment attachment) {
        entityManager.persist(attachment);
    }

    @Override
    public void update(Attachment attachment) {
        entityManager.merge(attachment);
    }
}
