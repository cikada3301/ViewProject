package com.training.vpalagin.project.converter.impl;

import com.training.vpalagin.project.converter.TicketConverter;
import com.training.vpalagin.project.dto.TicketDto;
import com.training.vpalagin.project.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketConverterImpl implements TicketConverter {
    @Override
    public Ticket convertFromDto(TicketDto ticketDto) {
        return Ticket.builder()
                .id(ticketDto.getId())
                .name(ticketDto.getName())
                .description(ticketDto.getDescription())
                .createdOn(ticketDto.getCreatedOn())
                .desiredResolutionDate(ticketDto.getDesiredResolutionDate())
                .assignee(ticketDto.getAssigneeId())
                .owner(ticketDto.getOwnerId())
                .state(ticketDto.getStateId())
                .urgency(ticketDto.getUrgencyId())
                .category(ticketDto.getCategory())
                .approver(ticketDto.getApproverId())
                .build();
    }

    @Override
    public TicketDto convertToDto(Ticket ticket) {
        return TicketDto.builder()
                .id(ticket.getId())
                .name(ticket.getName())
                .description(ticket.getDescription())
                .createdOn(ticket.getCreatedOn())
                .desiredResolutionDate(ticket.getDesiredResolutionDate())
                .assigneeId(ticket.getAssignee())
                .ownerId(ticket.getOwner())
                .stateId(ticket.getState())
                .urgencyId(ticket.getUrgency())
                .category(ticket.getCategory())
                .approverId(ticket.getApprover())
                .build();
    }
}