package com.training.vpalagin.project;

import com.training.vpalagin.project.converter.UserConverter;
import com.training.vpalagin.project.converter.impl.TicketConverterImpl;
import com.training.vpalagin.project.dto.ticket.TicketCreationDto;
import com.training.vpalagin.project.dto.ticket.TicketUpdateDto;
import com.training.vpalagin.project.dto.ticket.TicketViewDto;
import com.training.vpalagin.project.dto.user.UserViewDto;
import com.training.vpalagin.project.model.Category;
import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.User;
import com.training.vpalagin.project.model.enums.State;
import com.training.vpalagin.project.model.enums.Urgency;
import com.training.vpalagin.project.model.enums.UserRole;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class TicketConverterTest {

    @Mock
    private UserConverter userConverter;

    @InjectMocks
    private TicketConverterImpl ticketConverter;

    @Test
    public void testConvertFromCreationDto() {
        Ticket expected = Ticket.builder()
                .id(1L)
                .name("Broken pc")
                .description("fix CPU")
                .owner(User.builder()
                        .id(1L)
                        .firstName("Empl1")
                        .lastName("Empl1")
                        .email("empl@mail.ru")
                        .password("1111")
                        .role(UserRole.EMPLOYEE)
                        .build())
                .state(State.NEW)
                .urgency(Urgency.AVERAGE)
                .category(new Category(1L, "Application & Services"))
                .build();

        TicketCreationDto ticketCreationDto = TicketCreationDto.builder()
                .id(1L)
                .name("Broken pc")
                .description("fix CPU")
                .owner(UserViewDto.builder().id(1L).role(UserRole.EMPLOYEE).firstName("Empl1").lastName("Empl2").build())
                .state(State.NEW)
                .urgency(Urgency.AVERAGE)
                .category("Application & Services")
                .comment("")
                .build();
        Ticket actual = ticketConverter.convertFromCreationDto(ticketCreationDto);
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getState(), actual.getState());
        Assert.assertEquals(expected.getUrgency(), actual.getUrgency());
    }

    @Test
    public void testConvertFromUpdateDto() {
        Ticket expected = Ticket.builder()
                .id(1L)
                .name("Broken pc")
                .description("fix CPU")
                .owner(User.builder()
                        .id(1L)
                        .firstName("Empl1")
                        .lastName("Empl1")
                        .email("empl@mail.ru")
                        .password("1111")
                        .role(UserRole.EMPLOYEE)
                        .build())
                .state(State.NEW)
                .urgency(Urgency.AVERAGE)
                .category(new Category(1L, "Application & Services"))
                .build();

        TicketUpdateDto ticketUpdateDto = TicketUpdateDto.builder()
                .id(1L)
                .name("Broken pc")
                .description("fix CPU")
                .owner(UserViewDto.builder().id(1L).role(UserRole.EMPLOYEE).firstName("Empl1").lastName("Empl2").build())
                .state(State.NEW)
                .urgency(Urgency.AVERAGE)
                .category("Application & Services")
                .comment("")
                .build();
        Ticket actual = ticketConverter.convertFromUpdateDto(ticketUpdateDto);
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getState(), actual.getState());
        Assert.assertEquals(expected.getUrgency(), actual.getUrgency());
    }

    @Test
    public void testConvertFromViewDto() {
        Ticket expected = Ticket.builder()
                .id(1L)
                .name("Broken pc")
                .description("fix CPU")
                .owner(User.builder()
                        .id(1L)
                        .firstName("Empl1")
                        .lastName("Empl1")
                        .email("empl@mail.ru")
                        .password("1111")
                        .role(UserRole.EMPLOYEE)
                        .build())
                .state(State.NEW)
                .urgency(Urgency.AVERAGE)
                .category(new Category(1L, "Application & Services"))
                .build();

        TicketViewDto ticketViewDto = TicketViewDto.builder()
                .id(1L)
                .name("Broken pc")
                .description("fix CPU")
                .owner(UserViewDto.builder().id(1L).role(UserRole.EMPLOYEE).firstName("Empl1").lastName("Empl2").build())
                .state(State.NEW)
                .urgency(Urgency.AVERAGE)
                .build();
        Ticket actual = ticketConverter.convertFromViewDto(ticketViewDto);
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getState(), actual.getState());
        Assert.assertEquals(expected.getUrgency(), actual.getUrgency());
    }

    @Test
    public void testConvertToViewDto() {
        Ticket ticket = Ticket.builder()
                .id(1L)
                .name("Broken pc")
                .description("fix CPU")
                .owner(User.builder()
                        .id(1L)
                        .firstName("Empl1")
                        .lastName("Empl1")
                        .email("empl@mail.ru")
                        .password("1111")
                        .role(UserRole.EMPLOYEE)
                        .build())
                .state(State.NEW)
                .urgency(Urgency.AVERAGE)
                .category(new Category(1L, "Application & Services"))
                .build();

        TicketViewDto expected = TicketViewDto.builder()
                .id(1L)
                .name("Broken pc")
                .description("fix CPU")
                .owner(UserViewDto.builder().id(1L).role(UserRole.EMPLOYEE).firstName("Empl1").lastName("Empl2").build())
                .state(State.NEW)
                .urgency(Urgency.AVERAGE)
                .build();
        TicketViewDto actual = ticketConverter.convertToViewDto(ticket);
        Assert.assertEquals(actual.getName(), expected.getName());
        Assert.assertEquals(actual.getDescription(), expected.getDescription());
        Assert.assertEquals(actual.getState(), expected.getState());
        Assert.assertEquals(actual.getUrgency(), expected.getUrgency());
    }
}
