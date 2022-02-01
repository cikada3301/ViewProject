package com.training.vpalagin.project.dto.attachment;

import com.training.vpalagin.project.model.Ticket;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class AttachmentCreationDto {
    private Long id;

    private MultipartFile file;

    private Ticket ticket;

    private String name;
}
