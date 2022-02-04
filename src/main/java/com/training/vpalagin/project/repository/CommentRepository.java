package com.training.vpalagin.project.repository;

import com.training.vpalagin.project.model.Comment;

import java.util.List;

public interface CommentRepository {
    List<Comment> get(Long id);
    void add(Comment comment);
}
