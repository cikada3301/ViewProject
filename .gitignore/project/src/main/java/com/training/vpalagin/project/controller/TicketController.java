package com.training.vpalagin.project.controller;

import com.training.vpalagin.project.converter.TicketConverter;
import com.training.vpalagin.project.dto.TicketDto;
import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.enums.State;
import com.training.vpalagin.project.model.enums.Urgency;
import com.training.vpalagin.project.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final TicketConverter ticketConverter;

    public TicketController(TicketService ticketService, TicketConverter ticketConverter) {
        this.ticketService = ticketService;
        this.ticketConverter = ticketConverter;
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getAllTickets() {
        final List<TicketDto> users = ticketService.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/ascendingid")
    public ResponseEntity<List<Ticket>> getSortedAscendingTicketsById() {
        final List<Ticket> users = ticketService.getSortedAscendingTicketsById();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/descendingid")
    public ResponseEntity<List<Ticket>> getSortedDescendingTicketsById() {
        final List<Ticket> users = ticketService.getSortedDescendingTicketsById();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/alphabeticname")
    public ResponseEntity<List<Ticket>> getSortedAlphabeticOrderTicketsByName() {
        final List<Ticket> users = ticketService.getSortedAlphabeticOrderTicketsByName();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/invertedname")
    public ResponseEntity<List<Ticket>> getSortedInvertedOrderTicketsByName() {
        final List<Ticket> users = ticketService.getSortedInvertedOrderTicketsByName();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/ascendingdate")
    public ResponseEntity<List<Ticket>> getSortedAscendingTicketsByDate() {
        final List<Ticket> users = ticketService.getSortedAscendingTicketsByDate();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/descendingdate")
    public ResponseEntity<List<Ticket>> getSortedDescendingTicketsByDate() {
        final List<Ticket> users = ticketService.getSortedDescendingTicketsByDate();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/urgency")
    public ResponseEntity<List<Ticket>> getSortedTicketsByUrgency() {
        final List<Ticket> users = ticketService.getSortedTicketsByUrgency();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/invertedurgency")
    public ResponseEntity<List<Ticket>> getSortedInvertedTicketsByUrgency() {
        final List<Ticket> users = ticketService.getSortedInvertedTicketsByUrgency();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/alphabeticstatus")
    public ResponseEntity<List<Ticket>> getSortedAlphabeticOrderTicketsByStatus() {
        final List<Ticket> users = ticketService.getSortedAlphabeticOrderTicketsByStatus();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/invertedstatus")
    public ResponseEntity<List<Ticket>> getSortedInvertedOrderTicketsByStatus() {
        final List<Ticket> users = ticketService.getSortedInvertedTicketsByStatus();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<List<Ticket>> getTicketById(@PathVariable Long id) {
        final List<Ticket> users = ticketService.getTicketById(id);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Ticket>> getTicketByName(@PathVariable String name) {
        final List<Ticket> users = ticketService.getTicketByName(name);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Ticket>> getTicketByDate(@PathVariable Date date) {
        final List<Ticket> users = ticketService.getTicketByDate(date);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/urgency/{urgency}")
    public ResponseEntity<List<Ticket>> getTicketByUrgency(@PathVariable Urgency urgency) {
        final List<Ticket> users = ticketService.getTicketByUrgency(urgency);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<List<Ticket>> getTicketByState(@PathVariable State state) {
        final List<Ticket> users = ticketService.getTicketByState(state);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/add")
    public ResponseEntity<List<Ticket>> createTicket(@RequestBody TicketDto ticketDto) {
        Ticket ticket = ticketConverter.convertFromDto(ticketDto);
        ticketService.addTicket(ticket);
        return ResponseEntity.created(URI.create("tickets")).build();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable("id") Long id, @RequestBody TicketDto ticketDto) {
        Ticket ticket = ticketConverter.convertFromDto(ticketDto);
        ticketService.editTicket(id, ticket);
        return ResponseEntity.ok(ticket);
    }
}