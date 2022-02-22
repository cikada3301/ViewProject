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

    private static final String FROM_ATTACHMENT_A_ = "from Attachment a ";

    private static final String FROM_ATTACHMENT_A_WHERE_A_TICKET_ID_ID = "from Attachment a where a.ticket.id = :id";

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Attachment> getAll() {
        return entityManager
                .createQuery(FROM_ATTACHMENT_A_, Attachment.class)
                .getResultList();
    }

    @Override
    public Attachment getByTicketId(Long id) {
        return entityManager
                .createQuery(FROM_ATTACHMENT_A_WHERE_A_TICKET_ID_ID, Attachment.class)
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
