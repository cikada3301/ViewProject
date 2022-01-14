package com.training.vpalagin.project.controller;

import com.training.vpalagin.project.dto.TicketCreationDto;
import com.training.vpalagin.project.dto.TicketUpdateDto;
import com.training.vpalagin.project.dto.TicketViewDto;
import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.enums.Action;
import com.training.vpalagin.project.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/main-page")
    public ResponseEntity<List<TicketViewDto>> getAll() {
        final List<TicketViewDto> users = ticketService.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/sort/{param}")
    public ResponseEntity<List<Ticket>> sort(@PathVariable String param) {
        final List<Ticket> users = ticketService.sort(param);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/search/{param}")
    public ResponseEntity<List<TicketViewDto>> find(@PathVariable String param) {
        final List<TicketViewDto> tickets = ticketService.find(param);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/main-page/{id}")
    public ResponseEntity<Optional<TicketViewDto>> getTicket(@PathVariable("id") Long id) {
        final Optional<TicketViewDto> ticket = ticketService.getById(id);
        return ResponseEntity.ok(ticket);
    }

    @PostMapping("/create-ticket")
    public ResponseEntity<Void> create(@Valid @RequestBody TicketCreationDto ticketCreationDto) {
        ticketService.add(ticketCreationDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/create-ticket/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @Valid @RequestBody TicketUpdateDto ticketUpdateDto) {
        ticketService.edit(id, ticketUpdateDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/main-page/action/{id}")
    public ResponseEntity<Void> transitStatus(@PathVariable("id") Long id, @RequestParam Action action) {
        ticketService.transitStatus(id, action);
        return ResponseEntity.ok().build();
    }
}