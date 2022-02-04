package com.training.vpalagin.project.dto.ticket;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.training.vpalagin.project.dto.user.UserViewDto;
import com.training.vpalagin.project.model.Category;
import com.training.vpalagin.project.model.enums.Action;
import com.training.vpalagin.project.model.enums.State;
import com.training.vpalagin.project.model.enums.Urgency;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class TicketViewDto {

    private Long id;

    @Size(max = 100, message = "The name must not exceed 100 characters")
    private String name;

    @Size(max = 500, message = "The description must not exceed 500 characters")
    private String description;

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDateTime createdOn;

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDateTime desiredResolutionDate;

    private UserViewDto assignee;

    private UserViewDto owner;

    private State state;

    private Urgency urgency;

    private Category category;

    private UserViewDto approver;

    private List<Action> actionList;
}
