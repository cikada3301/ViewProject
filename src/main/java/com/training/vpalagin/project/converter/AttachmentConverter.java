package com.training.vpalagin.project.converter;

import com.training.vpalagin.project.dto.attachment.AttachmentViewDto;
import com.training.vpalagin.project.model.Attachment;
import com.training.vpalagin.project.model.Ticket;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AttachmentConverter {
    AttachmentViewDto convertToViewDto(Attachment attachment);

    Attachment convertAttachment(Ticket ticket, MultipartFile file) throws IOException;
}
