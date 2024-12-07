package com.rentalapp.rentalapi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representing an error response.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {

    @Schema(example = "Error message")
    private String message;

}
