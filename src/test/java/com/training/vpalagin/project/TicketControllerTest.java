package com.training.vpalagin.project;

import com.training.vpalagin.project.dto.ticket.TicketCreationDto;
import com.training.vpalagin.project.dto.ticket.TicketUpdateDto;
import com.training.vpalagin.project.model.enums.State;
import com.training.vpalagin.project.model.enums.Urgency;
import com.training.vpalagin.project.service.TicketService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/tickets/all?page=1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMy() throws Exception {
        mockMvc.perform(get("/api/tickets/my?page=1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(get("/api/tickets/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAdd() throws Exception {
        TicketCreationDto ticket = TicketCreationDto.builder()
                .id(11L)
                .name("Help with email")
                .description("how send email")
                .state(State.NEW)
                .urgency(Urgency.AVERAGE)
                .category("Application & Services")
                .comment("555 Error")
                .build();
        mockMvc.perform(post("/api/tickets/")
                        .flashAttr("ticketCreationDto", ticket)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddWithBlankName() throws Exception {
        TicketCreationDto ticket = TicketCreationDto.builder()
                .id(11L)
                .name("")
                .description("how send email")
                .state(State.NEW)
                .urgency(Urgency.AVERAGE)
                .category("Application & Services")
                .comment("555 Error")
                .build();
        mockMvc.perform(post("/api/tickets/")
                        .flashAttr("ticketCreationDto", ticket)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdate() throws Exception {
        TicketUpdateDto ticket = TicketUpdateDto.builder()
                .id(9L)
                .name("Help with email")
                .description("how send email")
                .state(State.NEW)
                .urgency(Urgency.AVERAGE)
                .category("Application & Services")
                .comment("555 Error")
                .build();
        mockMvc.perform(put("/api/tickets/9")
                        .flashAttr("ticketUpdateDto", ticket)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testUpdateWithBlankUrgency() throws Exception {
        TicketUpdateDto ticket = TicketUpdateDto.builder()
                .id(9L)
                .name("Help with email")
                .description("how send email")
                .state(State.NEW)
                .urgency(Urgency.AVERAGE)
                .category("Application & Services")
                .comment("555 Error")
                .build();
        mockMvc.perform(put("/api/tickets/9")
                        .flashAttr("ticketUpdateDto", ticket)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isNoContent());
    }
}
