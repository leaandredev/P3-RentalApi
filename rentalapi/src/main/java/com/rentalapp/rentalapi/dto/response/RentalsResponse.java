package com.rentalapp.rentalapi.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing a list of rental entities.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentalsResponse {

    /** List of rentals found */
    @Schema(description = "Rentals list")
    private List<RentalResponse> rentals;
}
