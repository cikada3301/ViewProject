package com.training.vpalagin.project.service;

import com.training.vpalagin.project.dto.comment.CommentMutationDto;
import com.training.vpalagin.project.dto.comment.CommentViewDto;

import java.util.List;

public interface CommentService {
    List<CommentViewDto> getAllByTicketId(Long id);

    void add(CommentMutationDto comment);
}
