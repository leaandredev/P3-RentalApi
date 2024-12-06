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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rentalapp.rentalapi.documentation.RentalControllerDocumentation;
import com.rentalapp.rentalapi.dto.request.RentalRequest;
import com.rentalapp.rentalapi.dto.response.OkResponse;
import com.rentalapp.rentalapi.dto.response.RentalResponse;
import com.rentalapp.rentalapi.dto.response.RentalsResponse;
import com.rentalapp.rentalapi.mapper.RentalMapper;
import com.rentalapp.rentalapi.model.Rental;
import com.rentalapp.rentalapi.model.User;
import com.rentalapp.rentalapi.service.RentalService;
import com.rentalapp.rentalapi.service.UserService;

/** Endpoints to handle rentals */
@RestController
@RequestMapping("/api/rentals")
public class RentalController implements RentalControllerDocumentation {

    private final RentalService rentalService;
    private final UserService userService;
    private final RentalMapper rentalMapper;

    public RentalController(RentalService rentalService, UserService userService, RentalMapper rentalMapper) {
        this.rentalService = rentalService;
        this.userService = userService;
        this.rentalMapper = rentalMapper;
    }

    @Override
    @GetMapping
    @ResponseBody
    public ResponseEntity<RentalsResponse> getAllRentals(Authentication authentication) {
        List<Rental> rentals = rentalService.getAllRentals();
        List<RentalResponse> rentalResponses = rentals.stream()
                .map(rentalMapper::entityToResponse)
                .toList();

        return ResponseEntity.ok(new RentalsResponse(rentalResponses));
    }

    @Override
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<RentalResponse> getRental(@PathVariable String id) {
        Rental rental = rentalService.getRentalById(Integer.valueOf(id));
        RentalResponse rentalResponse = rentalMapper.entityToResponse(rental);
        return ResponseEntity.ok(rentalResponse);
    }

    @Override
    @PostMapping
    @ResponseBody
    public ResponseEntity<OkResponse> createRental(@ModelAttribute RentalRequest rentalRequest,
            Authentication authentication) {
        User owner = userService.getUserByEmail(authentication.getName());
        Rental rental = rentalMapper.requestToEntity(rentalRequest, owner);
        rentalService.saveRental(rental);
        return ResponseEntity.ok(new OkResponse("Rental created !"));
    }

    @Override
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<OkResponse> updateRental(@PathVariable String id, @ModelAttribute RentalRequest rentalRequest) {

        rentalService.updateRental(Integer.valueOf(id), rentalRequest);
        return ResponseEntity.ok(new OkResponse("Rental updated !"));
    }

}
