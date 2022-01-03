package com.training.vpalagin.project.repository.impl;

import com.training.vpalagin.project.model.Attachment;
import com.training.vpalagin.project.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AttachmentRepositoryImpl implements AttachmentRepository {
    private final EntityManager entityManager;

    @Override
    public List<Attachment> getAll() {
        List<Attachment> attachments = entityManager
                .createQuery("from Attachment a ", Attachment.class)
                .getResultList();
        return attachments;
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
