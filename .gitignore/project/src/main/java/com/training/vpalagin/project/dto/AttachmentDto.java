package com.training.vpalagin.project.dto;

import com.training.vpalagin.project.model.Ticket;
import lombok.Data;

@Data
public class AttachmentDto {
    private Long id;

    private byte[] photo;

    private Ticket ticket;

    private String name;
}
