package com.training.vpalagin.project.service;

import com.training.vpalagin.project.dto.TicketDto;
import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.enums.Action;
import com.training.vpalagin.project.model.enums.State;
import com.training.vpalagin.project.model.enums.Urgency;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<TicketDto> getAll();

    List<Ticket> sort(String param);

    Optional<Ticket> find(String param);

    Optional<TicketDto> getById(Long id);

    void addTicket(TicketDto ticket);

    void editTicket(Long id, TicketDto ticket);

    void transitStatus(Long id, Action action);
}
