package com.rentalapp.rentalapi.documentation;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.rentalapp.rentalapi.dto.request.RentalRequest;
import com.rentalapp.rentalapi.dto.response.ErrorResponse;
import com.rentalapp.rentalapi.dto.response.OkResponse;
import com.rentalapp.rentalapi.dto.response.RentalResponse;
import com.rentalapp.rentalapi.dto.response.RentalsResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Rentals", description = "Operations related to rentals")
public interface RentalControllerDocumentation {

    @Operation(summary = "Get all rentals", description = "Retrieve all rentals")
    @ApiResponse(responseCode = "200", description = "Rentals retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RentalsResponse.class)))
    @ApiResponse(responseCode = "401", description = "Invalid credentials, access denied", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    public abstract ResponseEntity<RentalsResponse> getAllRentals(Authentication authentication);

    @Operation(summary = "Get Rental", description = "Get rental details")
    @ApiResponse(responseCode = "200", description = "Rental retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RentalResponse.class)))
    @ApiResponse(responseCode = "401", description = "Invalid credentials, access denied", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    public abstract ResponseEntity<RentalResponse> getRental(
            @Parameter(description = "Rental ID", required = true) String id);

    @Operation(summary = "Create Rental", description = "Create a new rental for authenticated user")
    @ApiResponse(responseCode = "200", description = "Rental successfully created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OkResponse.class)))
    @ApiResponse(responseCode = "401", description = "Invalid credentials, access denied", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public abstract ResponseEntity<OkResponse> createRental(
            @RequestBody(content = @Content(mediaType = "multipart/form-data", schema = @Schema(implementation = RentalRequest.class))) RentalRequest rentalRequest,
            Authentication authentication);

    @Operation(summary = "Update Rental", description = "Update a rental")
    @ApiResponse(responseCode = "200", description = "Rental successfully updated", content = @Content(schema = @Schema(implementation = OkResponse.class)))
    @ApiResponse(responseCode = "401", description = "invalid credentials, access denied", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<?> updateRental(
            @Parameter(description = "Rental ID", required = true) String id,
            @RequestBody(content = @Content(mediaType = "multipart/form-data", schema = @Schema(implementation = RentalRequest.class))) RentalRequest rentalRequest);
}
