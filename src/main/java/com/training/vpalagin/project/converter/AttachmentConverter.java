package com.training.vpalagin.project.converter;

import com.training.vpalagin.project.dto.attachment.AttachmentViewDto;
import com.training.vpalagin.project.dto.ticket.TicketCreationDto;
import com.training.vpalagin.project.model.Attachment;

import java.io.IOException;

public interface AttachmentConverter {
    Attachment convertFromDto(TicketCreationDto ticketCreationDto) throws IOException;
    AttachmentViewDto convertToViewDto(Attachment attachment);
}
