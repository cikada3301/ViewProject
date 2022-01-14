package com.training.vpalagin.project.service;

import com.training.vpalagin.project.dto.CommentCreationDto;
import com.training.vpalagin.project.dto.CommentDto;
import com.training.vpalagin.project.dto.CommentUpdateDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> get(Long id);
    void add(CommentCreationDto comment);
    void add(CommentUpdateDto comment);
}
