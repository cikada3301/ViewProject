package com.training.vpalagin.project.dto;

import com.training.vpalagin.project.model.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserViewDto {

    private Long id;

    private String firstName;

    private String lastName;

    private UserRole role;
}
