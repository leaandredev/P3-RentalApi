package com.rentalapp.rentalapi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representing an success response.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SuccessResponse {

    @Schema(example = "Success message")
    private String message;

}
