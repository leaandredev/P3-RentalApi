package com.rentalapp.rentalapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageRequest {

    @Schema(example = "Mon message")
    @NotBlank(message = "le champ message est obligatoire")
    @NotNull(message = "le champ message ne peut pas être null")
    private String message;

    @Schema(example = "2")
    @NotNull(message = "le champ user_id ne peut pas être null")
    private Integer user_id;

    @Schema(example = "5")
    @NotNull(message = "le champ rental_id ne peut pas être null")
    private Integer rental_id;

}
