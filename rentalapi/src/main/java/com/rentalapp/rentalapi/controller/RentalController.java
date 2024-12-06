package com.rentalapp.rentalapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentalapp.rentalapi.dto.request.RentalRequest;
import com.rentalapp.rentalapi.dto.response.ErrorResponse;
import com.rentalapp.rentalapi.dto.response.OkResponse;
import com.rentalapp.rentalapi.dto.response.RentalResponse;
import com.rentalapp.rentalapi.dto.response.RentalsResponse;
import com.rentalapp.rentalapi.mapper.RentalMapper;
import com.rentalapp.rentalapi.model.Rental;
import com.rentalapp.rentalapi.model.User;
import com.rentalapp.rentalapi.service.RentalService;
import com.rentalapp.rentalapi.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Rentals", description = "Operations related to rentals")
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;
    private final UserService userService;
    private final RentalMapper rentalMapper;

    public RentalController(RentalService rentalService, UserService userService, RentalMapper rentalMapper) {
        this.rentalService = rentalService;
        this.userService = userService;
        this.rentalMapper = rentalMapper;
    }

    @Operation(summary = "Get all rentals", description = "Retrieve all rentals")
    @ApiResponse(responseCode = "200", description = "Rentals retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RentalsResponse.class)))
    @ApiResponse(responseCode = "401", description = "Invalid credentials, access denied", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping
    public ResponseEntity<RentalsResponse> getAllRentals(Authentication authentication) {
        List<Rental> rentals = rentalService.getAllRentals();
        List<RentalResponse> rentalResponses = rentals.stream()
                .map(rentalMapper::entityToResponse)
                .toList();

        return ResponseEntity.ok(new RentalsResponse(rentalResponses));
    }

    @Operation(summary = "Get Rental", description = "Get rental details")
    @ApiResponse(responseCode = "200", description = "Rental retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RentalResponse.class)))
    @ApiResponse(responseCode = "401", description = "Invalid credentials, access denied", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping("/{id}")
    public ResponseEntity<?> getRental(
            @Parameter(description = "ID of the rental to get", required = true) @PathVariable String id) {
        Rental rental = rentalService.getRentalById(Integer.valueOf(id));
        return ResponseEntity.ok(rentalMapper.entityToResponse(rental));
    }

    @Operation(summary = "Create Rental", description = "Create a new rental for authenticated user")
    @ApiResponse(responseCode = "200", description = "Rental successfully created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OkResponse.class)))
    @ApiResponse(responseCode = "401", description = "Invalid credentials, access denied", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @PostMapping
    public ResponseEntity<?> createRental(
            @RequestBody(content = @Content(mediaType = "multipart/form-data", schema = @Schema(implementation = RentalRequest.class))) @ModelAttribute RentalRequest rentalRequest,
            Authentication authentication) {
        User owner = userService.getUserByEmail(authentication.getName());
        Rental rental = rentalMapper.requestToEntity(rentalRequest, owner);
        rentalService.saveRental(rental);
        return ResponseEntity.ok(new OkResponse("Rental created !"));
    }

    @Operation(summary = "Update Rental", description = "Update a rental")
    @ApiResponse(responseCode = "200", description = "Rental successfully updated", content = @Content(schema = @Schema(implementation = OkResponse.class)))
    @ApiResponse(responseCode = "401", description = "nvalid credentials, access denied", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRental(
            @Parameter(description = "ID of the rental to update", required = true) @PathVariable String id,
            @RequestBody(content = @Content(mediaType = "multipart/form-data", schema = @Schema(implementation = RentalRequest.class))) @ModelAttribute RentalRequest rentalRequest) {

        rentalService.updateRental(Integer.valueOf(id), rentalRequest);
        return ResponseEntity.ok(new OkResponse("Rental updated !"));
    }

}
