package com.training.vpalagin.project.converter.impl;

import com.training.vpalagin.project.converter.HistoryConverter;
import com.training.vpalagin.project.converter.TicketConverter;
import com.training.vpalagin.project.converter.UserConverter;
import com.training.vpalagin.project.dto.history.HistoryDto;
import com.training.vpalagin.project.dto.user.UserViewDto;
import com.training.vpalagin.project.model.History;
import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.User;
import com.training.vpalagin.project.model.enums.Action;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HistoryConverterImpl implements HistoryConverter {

    private final TicketConverter ticketConverter;
    private final UserConverter userConverter;

    @Override
    public History convertFromDto(HistoryDto historyDto) {
        Ticket ticket = ticketConverter.convertFromViewDto(historyDto.getTicket());
        User user = userConverter.convertFromViewDto(historyDto.getUser());
        return History.builder()
                .ticket(ticket)
                .date(historyDto.getDateAction())
                .action(historyDto.getAction())
                .user(user)
                .description(historyDto.getDescription())
                .build();
    }

    @Override
    public History convertHistory(Ticket ticketForEdit, Action create, UserViewDto owner) {
        return convertFromDto(HistoryDto.builder()
                .ticket(ticketConverter.convertToViewDto(ticketForEdit))
                .dateAction(ticketForEdit.getCreatedOn())
                .action(create)
                .user(owner)
                .description(ticketForEdit.getDescription())
                .build());
    }

    @Override
    public History convertHistory(Ticket ticket, User user) {
        return History.builder()
                .ticket(ticket)
                .action(Action.CREATE)
                .user(user)
                .description(ticket.getDescription())
                .build();
    }

    @Override
    public History convertToHistoryWithAttachment(Ticket ticket, User user) {
        return History.builder()
                .ticket(ticket)
                .action(Action.CREATE)
                .user(user)
                .description("Add file")
                .build();
    }

    @Override
    public History convertToHistoryWithAttachmentEdit(Ticket ticket, User user) {
        return History.builder()
                .ticket(ticket)
                .action(Action.CREATE)
                .user(user)
                .description("Edit file")
                .build();
    }

    @Override
    public History convertToHistoryWithAttachmentDelete(Ticket ticket, User user) {
        return History.builder()
                .ticket(ticket)
                .action(Action.CREATE)
                .user(user)
                .description("Delete file")
                .build();
    }
}
