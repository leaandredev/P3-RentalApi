package com.rentalapp.rentalapi.documentation;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.rentalapp.rentalapi.dto.request.RentalRequest;
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

/** RentalController Annotations for OpenAPI documentation */
@Tag(name = "Rentals", description = "Endpoints to handle rentals")
public interface RentalControllerDocumentation {

        /**
         * Get all rentals
         * 
         * @param authentication Represent the token for the authenticated user
         * @return a JSON with all rentals details
         */
        @Operation(summary = "Get all rentals", description = "Retrieve all rentals")
        @ApiResponse(responseCode = "200", description = "Rentals retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RentalsResponse.class)))
        public abstract ResponseEntity<RentalsResponse> getAllRentals(Authentication authentication);

        /**
         * Get rental details
         * 
         * @param id The rental ID
         * @return a JSON with the rental details
         */
        @Operation(summary = "Get Rental", description = "Get rental details")
        @ApiResponse(responseCode = "200", description = "Rental retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RentalResponse.class)))
        public abstract ResponseEntity<RentalResponse> getRental(
                        @Parameter(description = "Rental ID", required = true) String id);

        /**
         * Create a new rental for authenticated user
         * 
         * @param rentalRequest  a form-data request with details to create a new rental
         * @param authentication Represent the token for the authenticated user
         * @return a success message if rental successfully created
         */
        @Operation(summary = "Create Rental", description = "Create a new rental for authenticated user")
        @ApiResponse(responseCode = "200", description = "Rental successfully created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OkResponse.class)))
        public abstract ResponseEntity<OkResponse> createRental(
                        @RequestBody(content = @Content(mediaType = "multipart/form-data", schema = @Schema(implementation = RentalRequest.class))) RentalRequest rentalRequest,
                        Authentication authentication);

        /**
         * Update a rental
         * 
         * @param id            The rental ID
         * @param rentalRequest a form-data request with details to update a rental
         * @return a success message if rental successfully updated
         */
        @Operation(summary = "Update Rental", description = "Update a rental")
        @ApiResponse(responseCode = "200", description = "Rental successfully updated", content = @Content(schema = @Schema(implementation = OkResponse.class)))
        public ResponseEntity<OkResponse> updateRental(
                        @Parameter(description = "Rental ID", required = true) String id,
                        @RequestBody(content = @Content(mediaType = "multipart/form-data", schema = @Schema(implementation = RentalRequest.class))) RentalRequest rentalRequest);
}
