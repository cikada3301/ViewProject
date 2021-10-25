package com.training.vpalagin.project.dto;

import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {
    private Long id;

    private User user;

    private String text;

    private Date date;

    private Ticket ticket;
}
