package com.training.vpalagin.project.service;

import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.enums.Action;

import javax.mail.MessagingException;

public interface EmailService {
    void sendMessage(Ticket ticket, Action action) throws MessagingException;
}
