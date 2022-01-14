package com.training.vpalagin.project.converter.impl;

import com.training.vpalagin.project.converter.AttachmentConverter;
import com.training.vpalagin.project.dto.AttachmentDto;
import com.training.vpalagin.project.model.Attachment;
import org.springframework.stereotype.Component;

@Component
public class AttachmentConverterImpl implements AttachmentConverter {
    @Override
    public Attachment convertFromDto(AttachmentDto attachmentDto) {
        return Attachment.builder()
                .id(attachmentDto.getId())
                .name(attachmentDto.getName())
                .file(attachmentDto.getFile())
                .ticket(attachmentDto.getTicket())
                .build();
    }

    @Override
    public AttachmentDto convertToDto(Attachment attachment) {
        return AttachmentDto.builder()
                .id(attachment.getId())
                .name(attachment.getName())
                .file(attachment.getFile())
                .ticket(attachment.getTicket())
                .build();
    }
}
