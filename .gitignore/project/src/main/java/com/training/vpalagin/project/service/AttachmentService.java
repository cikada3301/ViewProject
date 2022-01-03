package com.training.vpalagin.project.service;

import com.training.vpalagin.project.model.Attachment;

import java.util.List;

public interface AttachmentService {
    List<Attachment> getAll();

    void add(Attachment attachment);

    void update(Attachment attachment);
}
