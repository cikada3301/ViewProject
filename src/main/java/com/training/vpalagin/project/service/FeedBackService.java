package com.training.vpalagin.project.service;

import com.training.vpalagin.project.dto.feedback.FeedbackDto;

import javax.mail.MessagingException;

public interface FeedBackService {
    void add(FeedbackDto feedBack) throws MessagingException;
}
