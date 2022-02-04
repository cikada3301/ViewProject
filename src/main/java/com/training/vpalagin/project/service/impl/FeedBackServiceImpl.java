package com.training.vpalagin.project.service.impl;

import com.training.vpalagin.project.converter.FeedbackConverter;
import com.training.vpalagin.project.dto.feedback.FeedbackDto;
import com.training.vpalagin.project.exception.NotAuthorizedException;
import com.training.vpalagin.project.model.enums.Action;
import com.training.vpalagin.project.model.enums.State;
import com.training.vpalagin.project.model.enums.UserRole;
import com.training.vpalagin.project.repository.FeedBackRepository;
import com.training.vpalagin.project.service.EmailService;
import com.training.vpalagin.project.service.FeedBackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;

@Service
@RequiredArgsConstructor
public class FeedBackServiceImpl implements FeedBackService {

    private final FeedBackRepository feedBackRepository;
    private final FeedbackConverter feedbackConverter;
    private final EmailService emailService;

    @Transactional
    @Override
    public void add(FeedbackDto feedBack) throws MessagingException {
        if (feedBack.getUser().getRole() == UserRole.ENGINEER) {
            throw new NotAuthorizedException("Not authorized");
        }
        if (feedBack.getTicket().getState() == State.DONE) {
            feedBackRepository.add(feedbackConverter.convertFromDto(feedBack));
            emailService.sendMessage(feedBack.getTicket(), Action.DONE);
        }
    }
}
