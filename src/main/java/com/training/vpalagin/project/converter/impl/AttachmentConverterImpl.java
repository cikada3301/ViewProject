package com.training.vpalagin.project.converter.impl;

import com.training.vpalagin.project.converter.AttachmentConverter;
import com.training.vpalagin.project.converter.TicketConverter;
import com.training.vpalagin.project.dto.attachment.AttachmentCreationDto;
import com.training.vpalagin.project.dto.attachment.AttachmentViewDto;
import com.training.vpalagin.project.dto.ticket.TicketCreationDto;
import com.training.vpalagin.project.model.Attachment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AttachmentConverterImpl implements AttachmentConverter {

    private final TicketConverter ticketConverter;

    @Override
    public Attachment convertFromDto(TicketCreationDto ticketCreationDto) throws IOException {
        return Attachment.builder()
                .name(ticketCreationDto.getName())
                .file(ticketCreationDto.getFile().getBytes())
                .ticket(ticketConverter.convertFromCreationDto(ticketCreationDto))
                .build();
    }

    @Override
    public AttachmentViewDto convertToViewDto(Attachment attachment) {
        return AttachmentViewDto.builder()
                .name(attachment.getName())
                .file(attachment.getFile())
                .build();
    }
}
