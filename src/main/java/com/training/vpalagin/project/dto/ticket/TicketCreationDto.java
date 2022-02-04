package com.training.vpalagin.project.dto.ticket;

import com.training.vpalagin.project.dto.user.UserViewDto;
import com.training.vpalagin.project.model.enums.State;
import com.training.vpalagin.project.model.enums.Urgency;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class TicketCreationDto {
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

    private MultipartFile file;

    public TicketCreationDto(Long id, @Size(max = 100, message = "The name must not exceed 100 characters") String name, @Size(max = 500, message = "The description must not exceed 500 characters") String description,  UserViewDto owner, State state, Urgency urgency, String category, String comment, MultipartFile file) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.state = state;
        this.urgency = urgency;
        this.category = category;
        this.comment = comment;
        this.file = file;
    }
}
