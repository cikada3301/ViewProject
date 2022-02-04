package com.training.vpalagin.project.service;

import com.training.vpalagin.project.dto.attachment.AttachmentViewDto;
import com.training.vpalagin.project.model.Attachment;

import java.util.List;

public interface AttachmentService {
    List<Attachment> getAll();

    AttachmentViewDto getById(Long id);

    void add(Attachment attachment);

    void update(Attachment attachment);
}
