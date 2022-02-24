package com.training.vpalagin.project.dto.feedback;

import com.training.vpalagin.project.dto.ticket.TicketViewDto;
import com.training.vpalagin.project.dto.user.UserViewDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeedbackDto {

    private Long id;

    private UserViewDto user;

    private Integer rate;

    private String text;

    private TicketViewDto ticket;
}
