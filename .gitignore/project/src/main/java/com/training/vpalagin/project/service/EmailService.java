package com.training.vpalagin.project.service;

import com.training.vpalagin.project.model.Ticket;

import javax.mail.MessagingException;

public interface EmailService {
    void sendMessage(Ticket ticket) throws MessagingException;
}
