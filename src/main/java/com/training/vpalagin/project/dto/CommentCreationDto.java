package com.training.vpalagin.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder
public class CommentCreationDto {

    private Long id;

    private UserViewDto user;

    @Size(max = 500, message = "Comment must not be more than 500 characters")
    private String text;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date date;

    private TicketCreationDto ticket;
}
