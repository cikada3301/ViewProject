package com.training.vpalagin.project.dto.attachment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttachmentViewDto {
    private String name;

    private byte[] file;
}
