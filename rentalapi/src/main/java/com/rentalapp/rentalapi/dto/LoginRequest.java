package com.rentalapp.rentalapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

@Data
public class LoginRequest {

    @Schema(example = "email@exemple.com")
    @NotBlank(message = "email field is required")
    @NonNull
    private String email;

    @Schema(example = "P@sswOrd!")
    @NotBlank(message = "password field is required")
    @NonNull
    private String password;
}
