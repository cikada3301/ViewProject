package com.training.vpalagin.project.service.impl;

import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.enums.Action;
import com.training.vpalagin.project.model.enums.State;
import com.training.vpalagin.project.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    public static final String url = "http://localhost:3000/main-page/";

    private final Map<Map<State, Action>, String> mapTemplates = Map.of(
            Map.of(State.DRAFT, Action.SUBMIT), "templatefirst",
            Map.of(State.NEW, Action.APPROVE), "templatesecond",
            Map.of(State.NEW, Action.DECLINE), "templatethird",
            Map.of(State.NEW, Action.CANCEL), "templatefourth",
            Map.of(State.APPROVED, Action.CANCEL), "templatefifth",
            Map.of(State.IN_PROGRESS, Action.DONE), "templatesixth",
            Map.of(State.DONE, Action.DONE), "templateseventh"
    );

    private final JavaMailSender mailSender;

    private final TemplateEngine htmlTemplateEngine;


    private void sendMailWithActionDoneToStateInDone(Ticket ticket, Action template) throws MessagingException {
        final Context ctx = new Context();
        ctx.setVariable("name", ticket.getOwner().getFirstName() + " " + ticket.getOwner().getLastName());

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setTo(ticket.getAssignee().getEmail());
        message.setFrom("papach2313@gmail.com");

        final String htmlContent = this.htmlTemplateEngine.process(mapTemplates.get(Map.of(ticket.getState(), template)), ctx);

        message.setText(htmlContent, true);

        this.mailSender.send(mimeMessage);
    }

    private void sendMailWithActionDoneToStateInProgress(Ticket ticket, Action template) throws MessagingException {
        final Context ctx = new Context();
        ctx.setVariable("name", ticket.getOwner().getFirstName() + " " + ticket.getOwner().getLastName());
        ctx.setVariable("ticketId", ticket.getId());
        ctx.setVariable("url", url);

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setTo(ticket.getOwner().getEmail());
        message.setFrom("papach2313@gmail.com");

        final String htmlContent = this.htmlTemplateEngine.process(mapTemplates.get(Map.of(ticket.getState(), template)), ctx);
        message.setText(htmlContent, true);

        this.mailSender.send(mimeMessage);
    }

    private void sendMailWithActionCancelToStateApproved(Ticket ticket, Action template) throws MessagingException {
        final Context ctx = new Context();
        ctx.setVariable("name", ticket.getOwner().getFirstName() + " " + ticket.getOwner().getLastName());
        ctx.setVariable("ticketId", ticket.getId());
        ctx.setVariable("url", url);

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setTo(new String[]{ticket.getOwner().getEmail(), ticket.getApprover().getEmail()});
        message.setFrom("papach2313@gmail.com");

        final String htmlContent = this.htmlTemplateEngine.process(mapTemplates.get(Map.of(ticket.getState(), template)), ctx);
        message.setText(htmlContent, true);

        this.mailSender.send(mimeMessage);
    }

    private void sendMailWithActionCancelToStateNew(Ticket ticket, Action template) throws MessagingException {
        final Context ctx = new Context();
        ctx.setVariable("name", ticket.getOwner().getFirstName() + " " + ticket.getOwner().getLastName());
        ctx.setVariable("ticketId", ticket.getId());
        ctx.setVariable("url", url);

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setTo(ticket.getOwner().getEmail());
        message.setFrom("papach2313@gmail.com");

        final String htmlContent = this.htmlTemplateEngine.process(mapTemplates.get(Map.of(ticket.getState(), template)), ctx);
        message.setText(htmlContent, true);

        this.mailSender.send(mimeMessage);
    }

    private void sendMailWithActionDecline(Ticket ticket, Action template) throws MessagingException {
        final Context ctx = new Context();
        ctx.setVariable("name", ticket.getOwner().getFirstName() + " " + ticket.getOwner().getLastName());
        ctx.setVariable("ticketId", ticket.getId());
        ctx.setVariable("url", url);

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setTo(ticket.getOwner().getEmail());
        message.setFrom("papach2313@gmail.com");

        final String htmlContent = this.htmlTemplateEngine.process(mapTemplates.get(Map.of(ticket.getState(), template)), ctx);
        message.setText(htmlContent, true);

        this.mailSender.send(mimeMessage);
    }

    private void sendMailWithActionApprove(Ticket ticket, Action template) throws MessagingException {
        final Context ctx = new Context();
        ctx.setVariable("ticketId", ticket.getId());
        ctx.setVariable("url", url);

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setTo(new String[]{"engineer1_mogilev@yopmail.com", "engineer2_mogilev@yopmail.com", ticket.getOwner().getEmail()});
        message.setFrom("papach2313@gmail.com");

        final String htmlContent = this.htmlTemplateEngine.process(mapTemplates.get(Map.of(ticket.getState(), template)), ctx);
        message.setText(htmlContent, true);

        this.mailSender.send(mimeMessage);
    }

    private void sendMailWithActionSubmit(Ticket ticket, Action template) throws MessagingException {
        final Context ctx = new Context();
        ctx.setVariable("ticketId", ticket.getId());
        ctx.setVariable("url", url);

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        final String htmlContent = this.htmlTemplateEngine.process(mapTemplates.get(Map.of(ticket.getState(), template)), ctx);
        message.setTo(new String[]{"manager1_mogilev@yopmail.com", "manager2_mogilev@yopmail.com"});
        message.setText(htmlContent, true);
        message.setFrom("papach2313@gmail.com");
        message.setSubject("New ticket was approval");

        this.mailSender.send(mimeMessage);
    }

    @Async
    @Override
    public void sendMessage(Ticket ticket, Action action) throws MessagingException {

        if (action == Action.SUBMIT && ticket.getState() == State.DRAFT) {
            sendMailWithActionSubmit(ticket, action);
        } else if (action == Action.APPROVE && ticket.getState() == State.NEW) {
            sendMailWithActionApprove(ticket, action);
        } else if (action == Action.DECLINE && ticket.getState() == State.NEW) {
            sendMailWithActionDecline(ticket, action);
        } else if (action == Action.CANCEL && ticket.getState() == State.NEW) {
            sendMailWithActionCancelToStateNew(ticket, action);
        } else if (action == Action.CANCEL && ticket.getState() == State.APPROVED) {
            sendMailWithActionCancelToStateApproved(ticket, action);
        } else if (action == Action.DONE && ticket.getState() == State.IN_PROGRESS) {
            sendMailWithActionDoneToStateInProgress(ticket, action);
        } else if (action == Action.DONE && ticket.getState() == State.DONE) {
            sendMailWithActionDoneToStateInDone(ticket, action);
        }
    }
}
