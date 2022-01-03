package com.training.vpalagin.project.service.impl;

import com.training.vpalagin.project.model.Feedback;
import com.training.vpalagin.project.model.enums.State;
import com.training.vpalagin.project.model.enums.UserRole;
import com.training.vpalagin.project.repository.FeedBackRepository;
import com.training.vpalagin.project.service.FeedBackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedBackServiceImpl implements FeedBackService {

    private final FeedBackRepository feedBackRepository;

    @Override
    public void add(Feedback feedBack) {
        if (feedBack.getUser().getRole() == UserRole.ENGINEER) {
            throw new RuntimeException("Not autorized");
        }
        if (feedBack.getTicket().getState() == State.DONE) {
            feedBackRepository.add(feedBack);
        }
    }
}
