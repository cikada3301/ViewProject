package com.training.vpalagin.project.converter.impl;

import com.training.vpalagin.project.converter.FeedbackConverter;
import com.training.vpalagin.project.converter.TicketConverter;
import com.training.vpalagin.project.converter.UserConverter;
import com.training.vpalagin.project.dto.feedback.FeedbackDto;
import com.training.vpalagin.project.model.Feedback;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FeedbackConverterImpl implements FeedbackConverter {

    private final UserConverter userConverter;
    private final TicketConverter ticketConverter;

    @Override
    public Feedback convertFromDto(FeedbackDto feedbackDto) {
        return Feedback.builder()
                .rate(feedbackDto.getRate())
                .text(feedbackDto.getText())
                .ticket(ticketConverter.convertFromViewDto(feedbackDto.getTicket()))
                .user(userConverter.convertFromViewDto(feedbackDto.getUser()))
                .build();
    }
}
