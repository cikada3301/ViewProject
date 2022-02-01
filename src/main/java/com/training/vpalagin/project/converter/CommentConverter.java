package com.training.vpalagin.project.converter;

import com.training.vpalagin.project.dto.comment.CommentCreationDto;
import com.training.vpalagin.project.dto.comment.CommentViewDto;
import com.training.vpalagin.project.dto.comment.CommentUpdateDto;
import com.training.vpalagin.project.model.Comment;

public interface CommentConverter {
    Comment convertFromCreationDto(CommentCreationDto commentDto);
    Comment convertFromUpdateDto(CommentUpdateDto commentDto);
    CommentViewDto convertToDto(Comment comment);
}
