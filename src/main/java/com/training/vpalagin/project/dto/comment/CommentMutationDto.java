package com.training.vpalagin.project.dto.comment;

import com.training.vpalagin.project.dto.ticket.TicketViewDto;
import com.training.vpalagin.project.dto.user.UserViewDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
public class CommentMutationDto {

    private Long id;

    private UserViewDto user;

    @Size(max = 500, message = "Comment must not be more than 500 characters")
    private String text;

    private TicketViewDto ticket;

    public CommentMutationDto(Long id, UserViewDto user, @Size(max = 500, message = "Comment must not be more than 500 characters") String text, TicketViewDto ticket) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.ticket = ticket;
    }
}
