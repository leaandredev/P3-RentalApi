package com.rentalapp.rentalapi.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentalsResponse {

    @Schema(description = "Liste des locations")
    private List<RentalResponse> rentals;
}
