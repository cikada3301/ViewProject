package com.training.vpalagin.project.controller;

import com.training.vpalagin.project.converter.impl.UserConverterImpl;
import com.training.vpalagin.project.dto.ticket.TicketCreationDto;
import com.training.vpalagin.project.dto.ticket.TicketUpdateDto;
import com.training.vpalagin.project.dto.ticket.TicketViewDto;
import com.training.vpalagin.project.model.Category;
import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.enums.Action;
import com.training.vpalagin.project.model.userDetails.JwtUser;
import com.training.vpalagin.project.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/all")
    public ResponseEntity<List<TicketViewDto>> getAll(@RequestParam("page") Long page) {
        final List<TicketViewDto> tickets = ticketService.getAll(page);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/my")
    public ResponseEntity<List<TicketViewDto>> getMy(@RequestParam("page") Long page, @AuthenticationPrincipal JwtUser user) {
        final List<TicketViewDto> tickets = ticketService.getMyTicket(page, user);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/sort/{param}")
    public ResponseEntity<List<Ticket>> sort(@PathVariable String param, @RequestParam("page") Long page) {
        final List<Ticket> tickets = ticketService.sort(param, page);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/search/{param}")
    public ResponseEntity<List<TicketViewDto>> find(@PathVariable String param, @RequestParam("page") Long page) {
        final List<TicketViewDto> tickets = ticketService.find(param, page);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TicketViewDto>> getTicket(@PathVariable("id") Long id) {
        final Optional<TicketViewDto> ticket = ticketService.getById(id);
        return ResponseEntity.ok(ticket);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity<Void> create(@Valid @ModelAttribute TicketCreationDto ticketCreationDto, @AuthenticationPrincipal JwtUser user) throws IOException {
        UserConverterImpl userConverter = new UserConverterImpl();
        ticketCreationDto.setOwner(userConverter.convertToViewDto(user.getUser()));
        ticketService.add(ticketCreationDto);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = {"multipart/form-data"})
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @Valid @ModelAttribute TicketUpdateDto ticketUpdateDto, @AuthenticationPrincipal JwtUser user) throws IOException {
        UserConverterImpl userConverter = new UserConverterImpl();
        ticketUpdateDto.setOwner(userConverter.convertToViewDto(user.getUser()));
        ticketService.edit(id, ticketUpdateDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{ticketId}/action")
    public ResponseEntity<Void> transitStatus(@PathVariable("ticketId") Long id, @RequestParam Action action, @AuthenticationPrincipal JwtUser user) throws MessagingException {
        ticketService.transitStatus(id, action, user);
        return ResponseEntity.ok().build();
    }
}