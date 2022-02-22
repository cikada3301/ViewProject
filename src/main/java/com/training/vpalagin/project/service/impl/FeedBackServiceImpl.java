package com.training.vpalagin.project.service.impl;

import com.training.vpalagin.project.converter.FeedbackConverter;
import com.training.vpalagin.project.converter.TicketConverter;
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
    private final TicketConverter ticketConverter;
    private final EmailService emailService;

    @Transactional
    @Override
    public void add(FeedbackDto feedbackDto) throws MessagingException {
        if (feedbackDto.getUser().getRole() == UserRole.ENGINEER) {
            throw new NotAuthorizedException("Not authorized");
        }
        if (feedbackDto.getTicket().getState() == State.DONE) {
            feedBackRepository.add(feedbackConverter.convertFromDto(feedbackDto));
            emailService.sendMessage(ticketConverter.convertFromViewDto(feedbackDto.getTicket()), Action.DONE);
        }
    }
}
