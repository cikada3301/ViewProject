package com.training.vpalagin.project.controller;

import com.training.vpalagin.project.dto.comment.CommentCreationDto;
import com.training.vpalagin.project.dto.comment.CommentViewDto;
import com.training.vpalagin.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ticket")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<List<CommentViewDto>> get(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.get(id));
    }

    @PostMapping("/comment")
    public ResponseEntity<Void> add(@Valid @RequestBody CommentCreationDto comment) {
        commentService.add(comment);
        return ResponseEntity.ok().build();
    }
}
