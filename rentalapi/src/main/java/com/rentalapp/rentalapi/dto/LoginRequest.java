package com.rentalapp.rentalapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

@Data
public class LoginRequest {

    @NotBlank(message = "email field is required")
    @NonNull
    private String email;

    @NotBlank(message = "password field is required")
    @NonNull
    private String password;
}
