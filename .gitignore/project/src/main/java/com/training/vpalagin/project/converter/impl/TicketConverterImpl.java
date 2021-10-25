package com.training.vpalagin.project.converter.impl;

import com.training.vpalagin.project.converter.TicketConverter;
import com.training.vpalagin.project.dto.TicketDto;
import com.training.vpalagin.project.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketConverterImpl implements TicketConverter {
    @Override
    public Ticket convertFromDto(TicketDto ticketDto) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDto.getId());
        ticket.setName(ticketDto.getName());
        ticket.setDescription(ticketDto.getDescription());
        ticket.setCreatedOn(ticketDto.getCreatedOn());
        ticket.setDesiredResolutionDate(ticketDto.getDesiredResolutionDate());
        ticket.setAssignee(ticketDto.getAssigneeId());
        ticket.setOwner(ticketDto.getOwnerId());
        ticket.setState(ticketDto.getStateId());
        ticket.setUrgency(ticketDto.getUrgencyId());
        ticket.setCategory(ticketDto.getCategory());
        ticket.setApprover(ticketDto.getApproverId());
        return ticket;
    }

    @Override
    public TicketDto convertToDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setId(ticketDto.getId());
        ticketDto.setName(ticket.getName());
        ticketDto.setDescription(ticket.getDescription());
        ticketDto.setCreatedOn(ticket.getCreatedOn());
        ticketDto.setDesiredResolutionDate(ticket.getDesiredResolutionDate());
        ticketDto.setAssigneeId(ticket.getAssignee());
        ticketDto.setOwnerId(ticket.getOwner());
        ticketDto.setStateId(ticket.getState());
        ticketDto.setUrgencyId(ticket.getUrgency());
        ticketDto.setCategory(ticket.getCategory());
        ticketDto.setApproverId(ticket.getApprover());
        return ticketDto;
    }

}
