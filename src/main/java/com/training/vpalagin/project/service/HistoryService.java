package com.training.vpalagin.project.service;

import com.training.vpalagin.project.model.History;

import java.util.List;

public interface HistoryService {
    List<History> getAllByTicketId(Long id);

    void add(History history);
}