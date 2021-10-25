package com.training.vpalagin.project.dto;

import com.training.vpalagin.project.model.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;

    private String firstName;

    private String lastName;

    private UserRole roleId;

    private String email;

    private String password;
}