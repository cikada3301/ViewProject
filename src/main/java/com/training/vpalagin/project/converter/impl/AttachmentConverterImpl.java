package com.training.vpalagin.project.converter.impl;

import com.training.vpalagin.project.converter.AttachmentConverter;
import com.training.vpalagin.project.converter.TicketConverter;
import com.training.vpalagin.project.dto.attachment.AttachmentViewDto;
import com.training.vpalagin.project.model.Attachment;
import com.training.vpalagin.project.model.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AttachmentConverterImpl implements AttachmentConverter {

    private final TicketConverter ticketConverter;

    @Override
    public AttachmentViewDto convertToViewDto(Attachment attachment) {
        return AttachmentViewDto.builder()
                .name(attachment.getName())
                .file(attachment.getFile())
                .build();
    }

    @Override
    public Attachment convertAttachment(Ticket ticket, MultipartFile file) throws IOException {
        return Attachment.builder()
                .file(file.getBytes())
                .name(file.getName())
                .ticket(ticket)
                .build();
    }
}
