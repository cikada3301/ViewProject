package com.training.vpalagin.project.controller;

import com.training.vpalagin.project.converter.TicketConverter;
import com.training.vpalagin.project.dto.TicketDto;
import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.enums.Action;
import com.training.vpalagin.project.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TicketController {

    private final TicketService ticketService;
    private final TicketConverter ticketConverter;

    @GetMapping("/main-page")
    public ResponseEntity<List<TicketDto>> getAll() {
        final List<TicketDto> users = ticketService.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/sort/{param}")
    public ResponseEntity<List<Ticket>> sort(@PathVariable("param") String param) {
        final List<Ticket> users = ticketService.sort(param);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/search/{param}")
    public ResponseEntity<Optional<Ticket>> find(@PathVariable String param) {
        final Optional<Ticket> ticket = ticketService.find(param);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/ticket-info/{id}}")
    public ResponseEntity<Optional<TicketDto>> getTicket(@PathVariable("id") Long id) {
        final Optional<TicketDto> ticket = ticketService.getById(id);
        return ResponseEntity.ok(ticket);
    }


    @PostMapping("/create-ticket")
    public ResponseEntity<Void> create(@RequestBody TicketDto ticketDto) {
        ticketService.addTicket(ticketDto);
        return ResponseEntity.created(URI.create("tickets")).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody TicketDto ticketDto) {
        ticketService.editTicket(id, ticketDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/action")
    public ResponseEntity<Void> transitStatus(@PathVariable("id") Long id, @RequestParam Action action) {
        ticketService.transitStatus(id, action);
        return ResponseEntity.ok().build();
    }
}