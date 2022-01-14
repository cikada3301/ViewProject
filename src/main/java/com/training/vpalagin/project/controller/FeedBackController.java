package com.training.vpalagin.project.controller;

import com.training.vpalagin.project.model.Feedback;
import com.training.vpalagin.project.service.FeedBackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class FeedBackController {

    private final FeedBackService feedBackService;

    @PostMapping("/{id}/feedback")
    public void add(Feedback feedBack) {
        feedBackService.add(feedBack);
    }
}
