package com.rentalapp.rentalapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OkResponse {
    @Schema(example = "Le champ email est obligatoire")
    private String message;

    public OkResponse() {
        this.message = null;
    }

    public OkResponse(String message) {
        this.message = message;
    }

}
