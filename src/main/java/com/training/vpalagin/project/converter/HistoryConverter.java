package com.training.vpalagin.project.converter;

import com.training.vpalagin.project.dto.history.HistoryDto;
import com.training.vpalagin.project.dto.user.UserViewDto;
import com.training.vpalagin.project.model.History;
import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.User;
import com.training.vpalagin.project.model.enums.Action;

public interface HistoryConverter {
    History convertFromDto(HistoryDto historyDto);

    History convertHistory(Ticket ticketForEdit, Action create, UserViewDto owner);

    History convertHistory(Ticket ticket, User user);
}
