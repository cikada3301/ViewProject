package com.training.vpalagin.project.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.User;
import com.training.vpalagin.project.model.enums.Action;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
public class HistoryDto {

    private TicketViewDto ticket;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dateAction;

    private Action action;

    private UserViewDto user;

    private String description;
}
