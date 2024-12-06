package com.rentalapp.rentalapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rentalapp.rentalapi.dto.request.RentalRequest;
import com.rentalapp.rentalapi.exception.NoEntryFoundException;
import com.rentalapp.rentalapi.mapper.RentalMapper;
import com.rentalapp.rentalapi.model.Rental;
import com.rentalapp.rentalapi.repository.RentalRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RentalService {

    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;

    public RentalService(RentalRepository rentalRepository, RentalMapper rentalMapper) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental getRentalById(Integer rentalId) {
        return rentalRepository.findById(rentalId)
                .orElseThrow(() -> new NoEntryFoundException("La location avec l'id " + rentalId + " n'existe pas."));
    }

    public Rental saveRental(Rental rental) {
        rentalRepository.save(rental);
        return rental;
    }

    public Rental updateRental(Integer rentalId, RentalRequest rentalRequest) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new NoEntryFoundException("La location avec l'id " + rentalId + " n'existe pas."));
        rentalMapper.updateEntityFromRequest(rental, rentalRequest);
        rentalRepository.save(rental);
        return rental;
    }

}
