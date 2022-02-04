package com.training.vpalagin.project.dto.history;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.training.vpalagin.project.dto.ticket.TicketViewDto;
import com.training.vpalagin.project.dto.user.UserViewDto;
import com.training.vpalagin.project.model.enums.Action;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class HistoryDto {

    private TicketViewDto ticket;

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDateTime dateAction;

    private Action action;

    private UserViewDto user;

    private String description;
}
