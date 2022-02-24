package com.training.vpalagin.project.dto.ticket;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.training.vpalagin.project.dto.user.UserViewDto;
import com.training.vpalagin.project.model.Category;
import com.training.vpalagin.project.model.enums.Action;
import com.training.vpalagin.project.model.enums.State;
import com.training.vpalagin.project.model.enums.Urgency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketViewDto {

    private Long id;

    @Size(max = 100, message = "The name must not exceed 100 characters")
    private String name;

    @Size(max = 500, message = "The description must not exceed 500 characters")
    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdOn;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date desiredResolutionDate;

    private UserViewDto assignee;

    private UserViewDto owner;

    private State state;

    private Urgency urgency;

    private Category category;

    private UserViewDto approver;

    private List<Action> actionList;
}
