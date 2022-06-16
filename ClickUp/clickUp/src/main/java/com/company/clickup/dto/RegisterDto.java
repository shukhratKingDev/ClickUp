package com.company.clickup.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class RegisterDto {
    @NotNull(message = "full name must not be empty")
    @Min(value = 3)
    private String fullName;

    @NotNull(message = "email must not be empty")
    @Email
    private String email;

    @NotNull(message = "password must not be empty")
    private String password;
}
