package com.training.vpalagin.project.controller;

import com.training.vpalagin.project.dto.ticket.TicketCreationDto;
import com.training.vpalagin.project.dto.ticket.TicketUpdateDto;
import com.training.vpalagin.project.dto.ticket.TicketViewDto;
import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.enums.Action;
import com.training.vpalagin.project.security.userDetails.JwtUser;
import com.training.vpalagin.project.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/all")
    public ResponseEntity<List<TicketViewDto>> getAll(@RequestParam("page") Long page, @AuthenticationPrincipal JwtUser user) {
        final List<TicketViewDto> tickets = ticketService.getAll(page, user);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/my")
    public ResponseEntity<List<TicketViewDto>> getMy(@RequestParam("page") Long page, @AuthenticationPrincipal JwtUser user) {
        final List<TicketViewDto> tickets = ticketService.getMy(page, user);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/sort/{param}")
    public ResponseEntity<List<Ticket>> sort(@PathVariable String param, @RequestParam("page") Long page, @AuthenticationPrincipal JwtUser user) {
        final List<Ticket> tickets = ticketService.sort(param, page, user);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/search/{param}")
    public ResponseEntity<List<TicketViewDto>> find(@PathVariable String param, @RequestParam("page") Long page, @AuthenticationPrincipal JwtUser user) {
        final List<TicketViewDto> tickets = ticketService.find(param, page, user);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketViewDto> getById(@PathVariable("id") Long id) {
        final TicketViewDto ticket = ticketService.getByTicketId(id);
        return ResponseEntity.ok(ticket);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> create(@Valid @ModelAttribute TicketCreationDto ticketCreationDto, @AuthenticationPrincipal JwtUser user) throws IOException {
        ticketService.add(ticketCreationDto, user);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{ticketId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> update(@PathVariable("ticketId") Long id, @Valid @ModelAttribute TicketUpdateDto ticketUpdateDto, @AuthenticationPrincipal JwtUser user) throws IOException {
        ticketService.edit(id, ticketUpdateDto, user);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{ticketId}/action")
    public ResponseEntity<Void> transitStatus(@PathVariable("ticketId") Long id, @RequestParam Action action, @AuthenticationPrincipal JwtUser user) throws MessagingException {
        ticketService.transitStatus(id, action, user);
        return ResponseEntity.ok().build();
    }
}
