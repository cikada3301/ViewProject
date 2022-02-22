package com.training.vpalagin.project.service.impl;

import com.training.vpalagin.project.model.History;
import com.training.vpalagin.project.repository.HistoryRepository;
import com.training.vpalagin.project.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<History> getAllByTicketId(Long id) {
        return historyRepository.getAllByTicketId(id)
                .stream()
                .sorted(Comparator.comparing(History::getDate))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(History history) {
        historyRepository.add(history);
    }
}
