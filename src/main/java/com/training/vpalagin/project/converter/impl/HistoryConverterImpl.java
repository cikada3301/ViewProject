package com.training.vpalagin.project.converter.impl;

import com.training.vpalagin.project.converter.HistoryConverter;
import com.training.vpalagin.project.converter.TicketConverter;
import com.training.vpalagin.project.converter.UserConverter;
import com.training.vpalagin.project.dto.history.HistoryDto;
import com.training.vpalagin.project.model.History;
import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.User;
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
}
