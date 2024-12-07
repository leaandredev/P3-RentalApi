package com.rentalapp.rentalapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

/**
 * Data Transfer Object (DTO) for handling login requests.
 * Contains the user's email and password with validation constraints.
 */
@Data
public class LoginRequest {

    /**
     * The email address of the user.
     * This field is mandatory and cannot be null or blank.
     */
    @Schema(example = "email@exemple.com")
    @NotBlank(message = "le champ email est obligatoire")
    @NotNull(message = "le champ email ne peut pas être null")
    @NonNull
    private String email;

    /**
     * The password of the user.
     * This field is mandatory and cannot be null or blank.
     */
    @Schema(example = "P@sswOrd!")
    @NotBlank(message = "le champ password est obligatoire")
    @NotNull(message = "le champ password ne peut pas être null")
    @NonNull
    private String password;
}
