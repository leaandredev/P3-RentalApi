package com.rentalapp.rentalapi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ErrorResponse {
    @Schema(example = "Le champ email est obligatoire")
    private String message;

    public ErrorResponse() {
        this.message = null;
    }

    public ErrorResponse(String message) {
        this.message = message;
    }

}
