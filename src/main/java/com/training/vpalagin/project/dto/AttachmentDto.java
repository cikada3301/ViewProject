package com.training.vpalagin.project.dto;

import com.training.vpalagin.project.model.Ticket;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttachmentDto {
    private Long id;

    private byte[] file;

    private Ticket ticket;

    private String name;
}
