package com.rentalapp.rentalapi.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.rentalapp.rentalapi.dto.request.RentalRequest;
import com.rentalapp.rentalapi.dto.response.RentalResponse;
import com.rentalapp.rentalapi.exception.NoEntryFoundException;
import com.rentalapp.rentalapi.mapper.RentalMapper;
import com.rentalapp.rentalapi.model.Rental;
import com.rentalapp.rentalapi.model.User;
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

    public List<RentalResponse> getAllRentals() {
        return rentalRepository.findAll().stream()
                .map(rental -> rentalMapper.entityToResponse(rental))
                .toList();
    }

    public RentalResponse getRentalById(Integer rentalId) {
        try {
            Rental rental = rentalRepository.findById(rentalId).get();
            return rentalMapper.entityToResponse(rental);
        } catch (NoSuchElementException e) {
            throw new NoEntryFoundException("La location avec l'id " + rentalId + " n'existe pas.");
        }
    }

    public Rental createRental(User owner, RentalRequest rentalRequest) {
        Rental rental = rentalMapper.requestToEntity(rentalRequest, owner);
        rentalRepository.save(rental);

        return rental;
    }

    public Rental updateRental(Integer rentalId, RentalRequest rentalRequest) {
        try {
            Rental rental = rentalRepository.findById(rentalId).get();

            rental.setName(rentalRequest.getName());
            rental.setPrice(Integer.valueOf(rentalRequest.getPrice()));
            rental.setSurface(Integer.valueOf(rentalRequest.getSurface()));
            rental.setDescription(rentalRequest.getDescription());

            rentalRepository.save(rental);
            return rental;
        } catch (NoSuchElementException e) {
            throw new NoEntryFoundException("La location avec l'id " + rentalId + " n'existe pas.");
        }
    }

}
