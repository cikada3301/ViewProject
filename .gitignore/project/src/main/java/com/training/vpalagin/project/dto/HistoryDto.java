package com.training.vpalagin.project.dto;


import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.User;
import com.training.vpalagin.project.model.enums.Action;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class HistoryDto {
    private Ticket ticket;

    private Date dateAction;

    private Action action;

    private User user;

    private String description;
}
