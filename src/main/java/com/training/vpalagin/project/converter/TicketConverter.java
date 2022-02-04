package com.training.vpalagin.project.converter;

import com.training.vpalagin.project.dto.ticket.TicketCreationDto;
import com.training.vpalagin.project.dto.ticket.TicketUpdateDto;
import com.training.vpalagin.project.dto.ticket.TicketViewDto;
import com.training.vpalagin.project.model.Ticket;


public interface TicketConverter {
    Ticket convertFromCreationDto(TicketCreationDto ticketCreationDto);
    Ticket convertFromUpdateDto(TicketUpdateDto ticketUpdateDto);
    Ticket convertFromViewDto(TicketViewDto ticketViewDto);
    TicketViewDto convertToViewDto(Ticket ticket);
    TicketCreationDto convertToCreationDto(Ticket ticket);
    TicketUpdateDto convertToUpdateDto(Ticket ticket);
}
