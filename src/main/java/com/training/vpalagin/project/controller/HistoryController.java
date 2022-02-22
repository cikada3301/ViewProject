package com.training.vpalagin.project.controller;

import com.training.vpalagin.project.model.History;
import com.training.vpalagin.project.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/histories")
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping("/{ticketId}")
    public ResponseEntity<List<History>> getAllByTicketId(@PathVariable("ticketId") Long id) {
        return ResponseEntity.ok(historyService.getAllByTicketId(id));
    }
}
