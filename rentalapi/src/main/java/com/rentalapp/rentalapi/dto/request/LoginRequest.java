package com.rentalapp.rentalapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class LoginRequest {

    @Schema(example = "email@exemple.com")
    @NotBlank(message = "le champ email est obligatoire")
    @NotNull(message = "le champ email ne peut pas être null")
    @NonNull
    private String email;

    @Schema(example = "P@sswOrd!")
    @NotBlank(message = "le champ password est obligatoire")
    @NotNull(message = "le champ password ne peut pas être null")
    @NonNull
    private String password;
}
