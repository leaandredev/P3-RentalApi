package com.rentalapp.rentalapi.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentalapp.rentalapi.dto.OkResponse;
import com.rentalapp.rentalapi.dto.RentalRequest;
import com.rentalapp.rentalapi.model.User;
import com.rentalapp.rentalapi.service.AuthService;
import com.rentalapp.rentalapi.service.RentalService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;
    private final AuthService authService;

    public RentalController(RentalService rentalService, AuthService authService) {
        this.rentalService = rentalService;
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<?> getAllRentals(Authentication authentication) {
        User owner = this.authService.getAuthenticatedUser(authentication);
        return ResponseEntity.ok(Map.of("rentals", rentalService.getAllByOwner(owner)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRental(@PathVariable String id) {
        return ResponseEntity.ok(rentalService.getRentalById(Integer.valueOf(id)));
    }

    @PostMapping
    public ResponseEntity<?> createRental(@ModelAttribute RentalRequest rentalRequest, Authentication authentication) {
        User owner = this.authService.getAuthenticatedUser(authentication);
        rentalService.createRental(owner, rentalRequest);
        return ResponseEntity.ok(new OkResponse("Rental created !"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRental(@PathVariable String id, @ModelAttribute RentalRequest rentalRequest) {
        rentalService.updateRental(Integer.valueOf(id), rentalRequest);
        return ResponseEntity.ok(new OkResponse("Rental updated !"));
    }

}
