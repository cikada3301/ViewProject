package com.training.vpalagin.project.service.impl;

import com.training.vpalagin.project.converter.CommentConverter;
import com.training.vpalagin.project.dto.comment.CommentMutationDto;
import com.training.vpalagin.project.dto.comment.CommentViewDto;
import com.training.vpalagin.project.logger.Logger;
import com.training.vpalagin.project.model.Comment;
import com.training.vpalagin.project.repository.CommentRepository;
import com.training.vpalagin.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentConverter commentConverter;

    @Override
    @Transactional(readOnly = true)
    public List<CommentViewDto> getAllByTicketId(Long id) {
        return commentRepository.getAllByTicketId(id).stream()
                .map(commentConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Logger
    @Transactional
    public void add(CommentMutationDto commentDto) {
        Comment comment = commentConverter.convertFromCreationDto(commentDto);
        commentRepository.add(comment);
    }
}
