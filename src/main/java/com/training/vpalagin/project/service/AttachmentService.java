package com.training.vpalagin.project.service;

import com.training.vpalagin.project.dto.attachment.AttachmentViewDto;
import com.training.vpalagin.project.model.Attachment;

public interface AttachmentService {

    AttachmentViewDto getByTicketId(Long id);

    void add(Attachment attachment);

    void deleteByTicketId(Long id);
}
