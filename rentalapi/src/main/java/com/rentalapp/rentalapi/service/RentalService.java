package com.rentalapp.rentalapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rentalapp.rentalapi.dto.RentalResponse;
import com.rentalapp.rentalapi.repository.RentalRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RentalService {

    private final RentalRepository rentalRepository;

    RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<RentalResponse> getAlls() {
        return rentalRepository.findAll().stream()
                .map(rental -> new RentalResponse(rental))
                .toList();
    }

}
