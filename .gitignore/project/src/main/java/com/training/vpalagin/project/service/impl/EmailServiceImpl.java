package com.training.vpalagin.project.service.impl;

import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private static final String EMAIL_TICKET_TEMPLATE = "html/ticket";

    private final JavaMailSender mailSender;

    private final TemplateEngine htmlTemplateEngine;

    @Override
    public void sendMessage(Ticket ticket) throws MessagingException {
        final Context ctx = new Context();
        ctx.setVariable("approver.firstName", ticket.getApprover().getFirstName());
        ctx.setVariable("approver.lastName", ticket.getApprover().getLastName());

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setSubject("Your ticket");
        message.setTo(ticket.getApprover().getFirstName());

        final String htmlContent = this.htmlTemplateEngine.process(EMAIL_TICKET_TEMPLATE, ctx);
        message.setText(htmlContent, true);
        this.mailSender.send(mimeMessage);
    }
}
