package com.training.vpalagin.project.controller;

import com.training.vpalagin.project.dto.comment.CommentMutationDto;
import com.training.vpalagin.project.dto.comment.CommentViewDto;
import com.training.vpalagin.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ticket")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{ticketId}")
    public ResponseEntity<List<CommentViewDto>> getAllByTicketId(@PathVariable("ticketId") Long id) {
        return ResponseEntity.ok(commentService.getAllByTicketId(id));
    }

    @PostMapping("/{ticketId}/comment")
    public ResponseEntity<Void> add(@Valid @RequestBody CommentMutationDto comment) {
        commentService.add(comment);
        return ResponseEntity.ok().build();
    }
}
