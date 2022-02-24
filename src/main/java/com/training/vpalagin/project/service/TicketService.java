package com.training.vpalagin.project.service;

import com.training.vpalagin.project.dto.ticket.TicketCreationDto;
import com.training.vpalagin.project.dto.ticket.TicketUpdateDto;
import com.training.vpalagin.project.dto.ticket.TicketViewDto;
import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.enums.Action;
import com.training.vpalagin.project.security.userDetails.JwtUser;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface TicketService {
    List<TicketViewDto> getAll(Long page, JwtUser jwtUser);

    List<TicketViewDto> getMy(Long page, JwtUser jwtUser);

    List<Ticket> sort(String param, Long page, JwtUser jwtUser);

    List<TicketViewDto> find(String param, Long page, JwtUser jwtUser);

    TicketViewDto getByTicketId(Long id);

    void add(TicketCreationDto ticket, JwtUser jwtUser) throws IOException;

    void edit(Long id, TicketUpdateDto ticket, JwtUser jwtUser) throws IOException;

    void transitStatus(Long id, Action action, JwtUser jwtUser) throws MessagingException;
}
