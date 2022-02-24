package com.training.vpalagin.project.controller;

import com.training.vpalagin.project.dto.feedback.FeedbackDto;
import com.training.vpalagin.project.service.FeedBackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
public class FeedBackController {

    private final FeedBackService feedBackService;

    @PostMapping("/{id}/feedback")
    public ResponseEntity<Void> add(@Valid @RequestBody FeedbackDto feedbackDto) throws MessagingException {
        feedBackService.add(feedbackDto);
        return ResponseEntity.noContent().build();
    }
}
