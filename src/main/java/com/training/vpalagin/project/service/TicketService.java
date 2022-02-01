package com.training.vpalagin.project.service;

import com.training.vpalagin.project.dto.ticket.TicketCreationDto;
import com.training.vpalagin.project.dto.ticket.TicketUpdateDto;
import com.training.vpalagin.project.dto.ticket.TicketViewDto;
import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.enums.Action;
import com.training.vpalagin.project.model.userDetails.JwtUser;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<TicketViewDto> getAll(Long page);

    List<TicketViewDto> getMyTicket(Long page, JwtUser jwtUser);

    List<Ticket> sort(String param, Long page);

    List<TicketViewDto> find(String param, Long page);

    Optional<TicketViewDto> getById(Long id);

    void add(TicketCreationDto ticket) throws IOException;

    void edit(Long id, TicketUpdateDto ticket) throws IOException;

    void transitStatus(Long id, Action action, JwtUser jwtUser) throws MessagingException;
}
