package com.training.vpalagin.project;

import com.training.vpalagin.project.converter.HistoryConverter;
import com.training.vpalagin.project.converter.TicketConverter;
import com.training.vpalagin.project.converter.UserConverter;
import com.training.vpalagin.project.dto.ticket.TicketCreationDto;
import com.training.vpalagin.project.dto.ticket.TicketUpdateDto;
import com.training.vpalagin.project.dto.ticket.TicketViewDto;
import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.User;
import com.training.vpalagin.project.model.enums.State;
import com.training.vpalagin.project.model.enums.Urgency;
import com.training.vpalagin.project.model.enums.UserRole;
import com.training.vpalagin.project.repository.TicketRepository;
import com.training.vpalagin.project.repository.UserRepository;
import com.training.vpalagin.project.security.userDetails.JwtUser;
import com.training.vpalagin.project.service.HistoryService;
import com.training.vpalagin.project.service.impl.TicketServiceImpl;
import com.training.vpalagin.project.service.transition.TicketTransitionMap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class TicketServiceTest {

    @Autowired
    private UserConverter userConverter;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TicketConverter ticketConverter;

    @Mock
    private HistoryConverter historyConverter;

    @Mock
    private HistoryService historyService;

    @Mock
    private TicketTransitionMap ticketTransitionMap;

    @InjectMocks
    private TicketServiceImpl ticketService;

    private final JwtUser jwtUser = new JwtUser(User.builder().email("user1_mogilev@yopmail.com").password("P@ssword1").role(UserRole.EMPLOYEE).build());

    @Test
    public void testById() {
        Ticket ticket =
                Ticket.builder()
                        .id(1L)
                        .name("Project")
                        .description("add repo")
                        .state(State.NEW)
                        .urgency(Urgency.AVERAGE)
                        .build();
        TicketViewDto expected =
                TicketViewDto.builder()
                        .id(1L)
                        .name("Project")
                        .description("add repo")
                        .state(State.NEW)
                        .urgency(Urgency.AVERAGE)
                        .build();
        Mockito.when(ticketRepository.getById(1L)).thenReturn(ticket);
        Mockito.when(ticketConverter.convertToViewDto(ticket)).thenReturn(expected);
        TicketViewDto actual = ticketService.getByTicketId(1L);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testAdd() throws IOException {
        TicketCreationDto ticket = TicketCreationDto.builder()
                .id(11L)
                .name("Help with email")
                .description("how send email")
                .state(State.NEW)
                .urgency(Urgency.AVERAGE)
                .category("Application & Services")
                .comment("")
                .build();
        Mockito.when(ticketConverter.convertFromCreationDto(ticket)).thenReturn(new Ticket());;
        ticketService.add(ticket, jwtUser);

        Mockito.verify(ticketRepository, Mockito.times(1)).add(any(Ticket.class));
    }

    @Test
    public void testUpdate() throws IOException {
        TicketUpdateDto ticket = TicketUpdateDto.builder()
                .id(9L)
                .name("Help with email")
                .description("how send email")
                .state(State.NEW)
                .urgency(Urgency.AVERAGE)
                .category("Application & Services")
                .comment("")
                .build();
        Mockito.when(ticketConverter.convertFromUpdateDto(ticket)).thenReturn(new Ticket());
        Mockito.when(ticketRepository.getById(9L)).thenReturn(new Ticket());
        ticketService.edit(9L, ticket, jwtUser);

        Mockito.verify(ticketRepository, Mockito.times(1)).update(any(Ticket.class));
    }
}
