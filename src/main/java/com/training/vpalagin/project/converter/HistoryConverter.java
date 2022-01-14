package com.training.vpalagin.project.converter;

import com.training.vpalagin.project.dto.HistoryDto;
import com.training.vpalagin.project.model.History;

public interface HistoryConverter {
    History convertFromDto(HistoryDto historyDto);
}
