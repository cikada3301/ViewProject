package com.training.vpalagin.project.repository;

import com.training.vpalagin.project.model.History;

import java.util.List;

public interface HistoryRepository {
    List<History> getAllByTicketId(Long id);

    void add(History history);
}
