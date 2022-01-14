package com.training.vpalagin.project.service;

import com.training.vpalagin.project.dto.TicketCreationDto;
import com.training.vpalagin.project.dto.TicketUpdateDto;
import com.training.vpalagin.project.dto.TicketViewDto;
import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.enums.Action;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<TicketViewDto> getAll();

    List<Ticket> sort(String param);

    List<TicketViewDto> find(String param);

    Optional<TicketViewDto> getById(Long id);

    void add(TicketCreationDto ticket);

    void edit(Long id, TicketUpdateDto ticket);

    void transitStatus(Long id, Action action);
}
