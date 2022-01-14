package com.training.vpalagin.project.repository;

import com.training.vpalagin.project.model.Attachment;

import java.util.List;

public interface AttachmentRepository {
    List<Attachment> getAll();

    void add(Attachment attachment);

    void update(Attachment attachment);
}
