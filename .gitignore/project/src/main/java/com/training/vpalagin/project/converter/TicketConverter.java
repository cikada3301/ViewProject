package com.training.vpalagin.project.converter;

import com.training.vpalagin.project.dto.TicketDto;
import com.training.vpalagin.project.model.Ticket;

import java.util.List;

public interface TicketConverter {
    Ticket convertFromDto(TicketDto ticketDto);

    TicketDto convertToDto(Ticket ticket);
}
