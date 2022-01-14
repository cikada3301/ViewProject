package com.training.vpalagin.project.service.impl;

import com.training.vpalagin.project.converter.CommentConverter;
import com.training.vpalagin.project.dto.CommentCreationDto;
import com.training.vpalagin.project.dto.CommentDto;
import com.training.vpalagin.project.dto.CommentUpdateDto;
import com.training.vpalagin.project.logger.Logger;
import com.training.vpalagin.project.model.Comment;
import com.training.vpalagin.project.repository.CommentRepository;
import com.training.vpalagin.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentConverter commentConverter;

    @Override
    @Logger
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CommentDto> get(Long id) {
        return commentRepository.get(id).stream().map(commentConverter::convertToDto).collect(Collectors.toList());
    }

    @Override
    @Logger
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void add(CommentCreationDto commentDto) {
        Comment comment = commentConverter.convertFromCreationDto(commentDto);
        commentRepository.add(comment);
    }

    @Override
    @Logger
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void add(CommentUpdateDto commentDto) {
        Comment comment = commentConverter.convertFromUpdateDto(commentDto);
        commentRepository.add(comment);
    }
}
