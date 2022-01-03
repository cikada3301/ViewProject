package com.training.vpalagin.project.converter.impl;

import com.training.vpalagin.project.converter.HistoryConverter;
import com.training.vpalagin.project.dto.HistoryDto;
import com.training.vpalagin.project.model.History;
import org.springframework.stereotype.Component;

@Component
public class HistoryConverterImpl implements HistoryConverter {
    @Override
    public History convertFromDto(HistoryDto historyDto) {
        return History.builder()
                .ticket(historyDto.getTicket())
                .dateAction(historyDto.getDateAction())
                .action(historyDto.getAction())
                .user(historyDto.getUser())
                .description(historyDto.getDescription())
                .build();
    }
}
