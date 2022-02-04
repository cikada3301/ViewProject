package com.training.vpalagin.project.dto.feedback;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.training.vpalagin.project.dto.user.UserViewDto;
import com.training.vpalagin.project.model.Ticket;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class FeedbackDto {

    private Long id;

    private UserViewDto user;

    private Integer rate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime date;

    private String text;

    private Ticket ticket;
}

