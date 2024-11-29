package com.rentalapp.rentalapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "email field is required")
    private String email;

    @NotBlank(message = "name field is required")
    private String name;

    @NotBlank(message = "password field is required")
    private String password;
}
