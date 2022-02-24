package com.training.vpalagin.project.converter.impl;

import com.training.vpalagin.project.converter.CommentConverter;
import com.training.vpalagin.project.converter.TicketConverter;
import com.training.vpalagin.project.converter.UserConverter;
import com.training.vpalagin.project.dto.comment.CommentMutationDto;
import com.training.vpalagin.project.dto.comment.CommentViewDto;
import com.training.vpalagin.project.dto.ticket.TicketViewDto;
import com.training.vpalagin.project.dto.user.UserViewDto;
import com.training.vpalagin.project.model.Comment;
import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentConverterImpl implements CommentConverter {

    private final TicketConverter ticketConverter;
    private final UserConverter userConverter;

    @Override
    public Comment convertFromCreationDto(CommentMutationDto commentMutationDto) {
        Ticket ticket = ticketConverter.convertFromViewDto(commentMutationDto.getTicket());
        User user = userConverter.convertFromViewDto(commentMutationDto.getUser());
        return Comment.builder()
                .id(commentMutationDto.getId())
                .text(commentMutationDto.getText())
                .ticket(ticket)
                .user(user)
                .build();
    }

    @Override
    public CommentViewDto convertToDto(Comment comment) {
        TicketViewDto ticket = ticketConverter.convertToViewDto(comment.getTicket());
        UserViewDto user = userConverter.convertToViewDto(comment.getUser());
        return CommentViewDto.builder()
                .id(comment.getId())
                .date(comment.getDate())
                .text(comment.getText())
                .ticket(ticket)
                .user(user)
                .build();
    }

    @Override
    public CommentMutationDto convertMutationDto(Ticket ticket, String comment, UserViewDto owner) {
        return CommentMutationDto.builder()
                .text(comment)
                .user(owner)
                .ticket(ticketConverter.convertToViewDto(ticket))
                .build();
    }
}
