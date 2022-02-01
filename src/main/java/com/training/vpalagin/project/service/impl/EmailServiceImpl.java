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
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private static final String EMAIL_TICKET_TEMPLATE = "template/templatesecond";

    private final JavaMailSender mailSender;

    private final TemplateEngine htmlTemplateEngine;

    @Transactional
    @Override
    public void sendMessage(Ticket ticket) throws MessagingException {
        final Context ctx = new Context();
        ctx.setVariable("ticket.id", ticket.getId());
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setSubject("Ticket was approved");
        message.setTo(ticket.getOwner().getEmail());
        final String htmlContent = this.htmlTemplateEngine.process(EMAIL_TICKET_TEMPLATE, ctx);
        message.setText(htmlContent, true);
        this.mailSender.send(mimeMessage);
    }
}
