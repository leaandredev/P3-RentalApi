package com.rentalapp.rentalapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "email field is required")
    private String email;

    @NotBlank(message = "password field is required")
    private String password;
}
