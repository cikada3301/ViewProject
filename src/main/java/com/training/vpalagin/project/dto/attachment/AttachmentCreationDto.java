package com.training.vpalagin.project.dto.attachment;

import com.training.vpalagin.project.model.Ticket;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
public class AttachmentCreationDto {
    private Long id;

    @Size(max = 5000000)
    private MultipartFile file;

    private Ticket ticket;

    @Pattern(regexp = "^[a-zA-Z0-9._ -]+\\.(pdf|doc|docx|png|jpeg|jpg)$", message = "The selected file type is not allowed. Please select a file of one of the following types: pdf, png, doc, docx, jpg, jpeg")
    private String name;
}
