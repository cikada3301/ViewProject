package com.training.vpalagin.project.converter;

import com.training.vpalagin.project.dto.feedback.FeedbackDto;
import com.training.vpalagin.project.model.Feedback;

public interface FeedbackConverter {
    Feedback convertFromDto(FeedbackDto feedbackDto);
}
