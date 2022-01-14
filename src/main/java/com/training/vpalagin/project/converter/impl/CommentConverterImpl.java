package com.training.vpalagin.project.converter.impl;

import com.training.vpalagin.project.converter.CommentConverter;
import com.training.vpalagin.project.converter.TicketConverter;
import com.training.vpalagin.project.converter.UserConverter;
import com.training.vpalagin.project.dto.*;
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
    public Comment convertFromDto(CommentDto commentDto) {
        Ticket ticket = ticketConverter.convertFromViewDto(commentDto.getTicket());
        User user = userConverter.convertFromViewDto(commentDto.getUser());
        return Comment.builder()
                .id(commentDto.getId())
                .date(commentDto.getDate())
                .text(commentDto.getText())
                .ticket(ticket)
                .user(user)
                .build();
    }

    @Override
    public Comment convertFromCreationDto(CommentCreationDto commentCreationDto) {
        Ticket ticket = ticketConverter.convertFromCreationDto(commentCreationDto.getTicket());
        User user = userConverter.convertFromViewDto(commentCreationDto.getUser());
        return Comment.builder()
                .id(commentCreationDto.getId())
                .date(commentCreationDto.getDate())
                .text(commentCreationDto.getText())
                .ticket(ticket)
                .user(user)
                .build();
    }

    @Override
    public Comment convertFromUpdateDto(CommentUpdateDto commentUpdateDto) {
        Ticket ticket = ticketConverter.convertFromUpdateDto(commentUpdateDto.getTicket());
        User user = userConverter.convertFromViewDto(commentUpdateDto.getUser());
        return Comment.builder()
                .id(commentUpdateDto.getId())
                .date(commentUpdateDto.getDate())
                .text(commentUpdateDto.getText())
                .ticket(ticket)
                .user(user)
                .build();
    }

    @Override
    public CommentDto convertToDto(Comment comment) {
        TicketViewDto ticket = ticketConverter.convertToViewDto(comment.getTicket());
        UserViewDto user = userConverter.convertToViewDto(comment.getUser());
        return CommentDto.builder()
                .id(comment.getId())
                .date(comment.getDate())
                .text(comment.getText())
                .ticket(ticket)
                .user(user)
                .build();
    }

    @Override
    public CommentCreationDto convertToCreateDto(Comment comment) {
        TicketCreationDto ticket = ticketConverter.convertToCreationDto(comment.getTicket());
        UserViewDto user = userConverter.convertToViewDto(comment.getUser());
        return CommentCreationDto.builder()
                .id(comment.getId())
                .date(comment.getDate())
                .text(comment.getText())
                .ticket(ticket)
                .user(user)
                .build();
    }
}