package com.training.vpalagin.project.converter.impl;

import com.training.vpalagin.project.converter.TicketConverter;
import com.training.vpalagin.project.converter.UserConverter;
import com.training.vpalagin.project.dto.ticket.TicketCreationDto;
import com.training.vpalagin.project.dto.ticket.TicketUpdateDto;
import com.training.vpalagin.project.dto.ticket.TicketViewDto;
import com.training.vpalagin.project.dto.user.UserViewDto;
import com.training.vpalagin.project.model.Category;
import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.User;
import com.training.vpalagin.project.model.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketConverterImpl implements TicketConverter {

    private final UserConverter userConverter;

    @Override
    public Ticket convertFromCreationDto(TicketCreationDto ticketCreationDto) {
        User owner = userConverter.convertFromViewDto(ticketCreationDto.getOwner());
        return Ticket.builder()
                .id(ticketCreationDto.getId())
                .name(ticketCreationDto.getName())
                .description(ticketCreationDto.getDescription())
                .owner(owner)
                .state(ticketCreationDto.getState())
                .urgency(ticketCreationDto.getUrgency())
                .category(new Category(null, ticketCreationDto.getCategory()))
                .approver(new User(1L, "", "", UserRole.EMPLOYEE, "", ""))
                .assignee(new User(1L, "", "", UserRole.EMPLOYEE, "", ""))
                .build();
    }

    @Override
    public Ticket convertFromUpdateDto(TicketUpdateDto ticketUpdateDto) {
        User owner = userConverter.convertFromViewDto(ticketUpdateDto.getOwner());
        return Ticket.builder()
                .id(ticketUpdateDto.getId())
                .name(ticketUpdateDto.getName())
                .description(ticketUpdateDto.getDescription())
                .owner(owner)
                .state(ticketUpdateDto.getState())
                .urgency(ticketUpdateDto.getUrgency())
                .category(new Category(null, ticketUpdateDto.getCategory()))
                .approver(new User(1L, "", "", UserRole.EMPLOYEE, "", ""))
                .assignee(new User(1L, "", "", UserRole.EMPLOYEE, "", ""))
                .build();
    }

    @Override
    public Ticket convertFromViewDto(TicketViewDto ticketViewDto) {
        User assignee = userConverter.convertFromViewDto(ticketViewDto.getAssignee());
        User owner = userConverter.convertFromViewDto(ticketViewDto.getOwner());
        User approver = userConverter.convertFromViewDto(ticketViewDto.getApprover());
        return Ticket.builder()
                .id(ticketViewDto.getId())
                .name(ticketViewDto.getName())
                .description(ticketViewDto.getDescription())
                .createdOn(ticketViewDto.getCreatedOn())
                .desiredResolutionDate(ticketViewDto.getDesiredResolutionDate())
                .assignee(assignee)
                .owner(owner)
                .state(ticketViewDto.getState())
                .urgency(ticketViewDto.getUrgency())
                .category(ticketViewDto.getCategory())
                .approver(approver)
                .build();
    }

    @Override
    public TicketViewDto convertToViewDto(Ticket ticket) {
        UserViewDto assignee = userConverter.convertToViewDto(ticket.getAssignee());
        UserViewDto owner = userConverter.convertToViewDto(ticket.getOwner());
        UserViewDto approver = userConverter.convertToViewDto(ticket.getApprover());

        return TicketViewDto.builder()
                .id(ticket.getId())
                .name(ticket.getName())
                .description(ticket.getDescription())
                .createdOn(ticket.getCreatedOn())
                .desiredResolutionDate(ticket.getDesiredResolutionDate())
                .assignee(assignee)
                .owner(owner)
                .state(ticket.getState())
                .urgency(ticket.getUrgency())
                .category(ticket.getCategory())
                .approver(approver)
                .build();
    }

    @Override
    public TicketCreationDto convertToCreationDto(Ticket ticket) {
        UserViewDto owner = userConverter.convertToViewDto(ticket.getOwner());
        return TicketCreationDto.builder()
                .id(ticket.getId())
                .name(ticket.getName())
                .description(ticket.getDescription())
                .owner(owner)
                .state(ticket.getState())
                .urgency(ticket.getUrgency())
                .category(ticket.getCategory().getName())
                .build();
    }

    @Override
    public TicketUpdateDto convertToUpdateDto(Ticket ticket) {
        UserViewDto owner = userConverter.convertToViewDto(ticket.getOwner());
        return TicketUpdateDto.builder()
                .id(ticket.getId())
                .name(ticket.getName())
                .description(ticket.getDescription())
                .owner(owner)
                .category(ticket.getCategory().getName())
                .state(ticket.getState())
                .urgency(ticket.getUrgency())
                .build();
    }
}