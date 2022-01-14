package com.training.vpalagin.project.converter;

import com.training.vpalagin.project.dto.CommentCreationDto;
import com.training.vpalagin.project.dto.CommentDto;
import com.training.vpalagin.project.dto.CommentUpdateDto;
import com.training.vpalagin.project.model.Comment;

public interface CommentConverter {
    Comment convertFromDto(CommentDto commentDto);
    Comment convertFromCreationDto(CommentCreationDto commentDto);
    Comment convertFromUpdateDto(CommentUpdateDto commentDto);
    CommentDto convertToDto(Comment comment);
    CommentCreationDto convertToCreateDto(Comment comment);
}
