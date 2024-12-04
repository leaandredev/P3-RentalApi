package com.rentalapp.rentalapi.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentalapp.rentalapi.service.RentalService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public ResponseEntity<?> getAllRentals() {
        return ResponseEntity.ok(Map.of("rentals", rentalService.getAlls()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRental(@PathVariable String id) {
        return ResponseEntity.ok(rentalService.getRentalById(Integer.valueOf(id)));
    }

}
