package com.company.clickup.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterDto {
    @NotNull(message = "full name must not be empty")
    private String fullName;

    @NotNull(message = "email must not be empty")
    private String email;

    @NotNull(message = "password must not be empty")
    private String password;
}
