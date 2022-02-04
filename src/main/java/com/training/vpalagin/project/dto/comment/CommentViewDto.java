package com.training.vpalagin.project.dto.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.training.vpalagin.project.dto.ticket.TicketViewDto;
import com.training.vpalagin.project.dto.user.UserViewDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
public class CommentViewDto {

    private Long id;

    private UserViewDto user;

    @Size(max = 500, message = "Comment must not be more than 500 characters")
    private String text;

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDateTime date;

    private TicketViewDto ticket;
}
