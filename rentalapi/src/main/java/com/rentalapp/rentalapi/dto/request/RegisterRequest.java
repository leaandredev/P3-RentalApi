package com.rentalapp.rentalapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for handling user registration requests.
 * Contains the user's email, name, and password with validation constraints.
 */
@Data
public class RegisterRequest {

    /**
     * The email address of the user.
     * This field is mandatory and cannot be null or blank.
     */
    @Schema(example = "email@exemple.com")
    @NotBlank(message = "The email field is required.")
    @NotNull(message = "The email field cannot be null.")
    private String email;

    /**
     * The name of the user (firstName and/or lastName)
     * This field is mandatory and cannot be null or blank.
     */
    @Schema(example = "Prenom NOM")
    @NotBlank(message = "The name field is required.")
    @NotNull(message = "The name field cannot be null.")
    private String name;

    /**
     * The password chosen by the user.
     * This field is mandatory and cannot be null or blank.
     */
    @Schema(example = "P@sswOrd!")
    @NotBlank(message = "The password field is required.")
    @NotNull(message = "The password field cannot be null.")
    private String password;
}
