package com.training.vpalagin.project.dto.ticket;

import com.training.vpalagin.project.dto.user.UserViewDto;
import com.training.vpalagin.project.model.enums.State;
import com.training.vpalagin.project.model.enums.Urgency;
import com.training.vpalagin.project.validation.ValidFile;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class TicketUpdateDto {
    private Long id;

    @Size(max = 100, message = "The name must not exceed 100 characters")
    @NotBlank
    private String name;

    @Size(max = 500, message = "The description must not exceed 500 characters")
    private String description;

    private UserViewDto owner;

    private State state;

    private Urgency urgency;

    private String category;

    private String comment;

    @ValidFile
    private MultipartFile file;
}
