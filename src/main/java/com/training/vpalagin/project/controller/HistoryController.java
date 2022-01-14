package com.training.vpalagin.project.controller;

import com.training.vpalagin.project.model.History;
import com.training.vpalagin.project.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping("/history/{id}")
    public ResponseEntity<List<History>> get(@PathVariable Long id) {
        return ResponseEntity.ok(historyService.get(id));
    }
}
