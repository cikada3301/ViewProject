package com.training.vpalagin.project.service;

import com.training.vpalagin.project.dto.comment.CommentCreationDto;
import com.training.vpalagin.project.dto.comment.CommentViewDto;
import com.training.vpalagin.project.dto.comment.CommentUpdateDto;

import java.util.List;

public interface CommentService {
    List<CommentViewDto> get(Long id);
    void add(CommentCreationDto comment);
    void add(CommentUpdateDto comment);
}
