package com.training.vpalagin.project.converter;

import com.training.vpalagin.project.dto.comment.CommentMutationDto;
import com.training.vpalagin.project.dto.comment.CommentViewDto;
import com.training.vpalagin.project.dto.user.UserViewDto;
import com.training.vpalagin.project.model.Comment;
import com.training.vpalagin.project.model.Ticket;

public interface CommentConverter {
    Comment convertFromCreationDto(CommentMutationDto commentDto);

    CommentViewDto convertToDto(Comment comment);

    CommentMutationDto convertMutationDto(Ticket ticket, String comment, UserViewDto owner);
}
