package com.rentalapp.rentalapi.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RentalsResponse {

    @Schema(description = "Liste des locations")
    private List<RentalResponse> rentals;
}
