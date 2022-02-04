package com.training.vpalagin.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
public class UserAuthenticationDto {

    @Email
    @Size(max = 100, message = "Password must be less than 100 characters")
    @NotBlank(message = "Please fill out the required field")
    private String email;

    @Size(min = 6, max = 20, message = "Please make sure you are using a valid email or password")
    @NotBlank(message = "Please fill out the required field")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Please make sure you are using a valid email or password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}