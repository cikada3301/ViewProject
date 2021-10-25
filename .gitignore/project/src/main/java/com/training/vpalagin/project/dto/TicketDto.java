package com.training.vpalagin.project.dto;

import com.training.vpalagin.project.model.Category;
import com.training.vpalagin.project.model.User;
import com.training.vpalagin.project.model.enums.State;
import com.training.vpalagin.project.model.enums.Urgency;
import lombok.Data;
import java.util.Date;

@Data
public class TicketDto {

    private Long id;

    private String name;

    private String description;

    private String createdOn;

    private Date desiredResolutionDate;

    private User assigneeId;

    private User ownerId;

    private State stateId;

    private Urgency urgencyId;

    private Category category;

    private User approverId;
}
