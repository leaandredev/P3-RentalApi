package com.rentalapp.rentalapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {

    @Schema(example = "email@exemple.com")
    @NotBlank(message = "le champ email est obligatoire")
    @NotNull(message = "le champ email ne peut pas être null")
    private String email;

    @Schema(example = "Prenom NOM")
    @NotBlank(message = "le champ name est obligatoire")
    @NotNull(message = "le champ name ne peut pas être null")
    private String name;

    @Schema(example = "P@sswOrd!")
    @NotBlank(message = "le champ password est obligatoire")
    @NotNull(message = "le champ password ne peut pas être null")
    private String password;
}
