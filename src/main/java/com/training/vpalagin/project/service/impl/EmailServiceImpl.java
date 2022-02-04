package com.training.vpalagin.project.service.impl;

import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.enums.Action;
import com.training.vpalagin.project.model.enums.State;
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
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final Map<Map<State, Action>, String> mapTemplates = Map.of(
            Map.of(State.DRAFT, Action.SUBMIT), "template/templatefirst",
            Map.of(State.NEW, Action.APPROVE),  "template/templatesecond",
            Map.of(State.NEW, Action.DECLINE), "template/templatethird",
            Map.of(State.NEW, Action.CANCEL), "template/templatefourth",
            Map.of(State.APPROVED, Action.CANCEL), "template/templatefifth",
            Map.of(State.IN_PROGRESS, Action.DONE), "template/templatesixth",
            Map.of(State.DONE, Action.DONE), "template/templateseventh"
    );

    private final JavaMailSender mailSender;

    private final TemplateEngine htmlTemplateEngine;

    @Transactional
    @Override
    public void sendMessage(Ticket ticket, Action action) throws MessagingException {
        final Context ctx = new Context();
        ctx.setVariable("ticket.id", ticket.getId());
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        System.out.println(ticket);
        message.setTo(ticket.getOwner().getEmail());
        final String htmlContent = this.htmlTemplateEngine.process(mapTemplates.get(Map.of(ticket.getState(), action)), ctx);
        message.setText(htmlContent, true);
        this.mailSender.send(mimeMessage);
    }
}
