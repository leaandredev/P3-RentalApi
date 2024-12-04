package com.rentalapp.rentalapi.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.rentalapp.rentalapi.dto.RentalRequest;
import com.rentalapp.rentalapi.dto.RentalResponse;
import com.rentalapp.rentalapi.exception.NoEntryFoundException;
import com.rentalapp.rentalapi.model.Rental;
import com.rentalapp.rentalapi.model.User;
import com.rentalapp.rentalapi.repository.RentalRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RentalService {

    private final RentalRepository rentalRepository;

    private final FileService fileService;

    public RentalService(RentalRepository rentalRepository, FileService fileService) {
        this.rentalRepository = rentalRepository;
        this.fileService = fileService;
    }

    public List<RentalResponse> getAlls() {
        return rentalRepository.findAll().stream()
                .map(rental -> new RentalResponse(rental))
                .toList();
    }

    public List<RentalResponse> getAllByOwner(User owner) {
        return rentalRepository.findByOwner(owner).stream()
                .map(rental -> new RentalResponse(rental))
                .toList();
    }

    public RentalResponse getRentalById(Integer rentalId) {
        try {
            Rental rental = rentalRepository.findById(rentalId).get();
            return new RentalResponse(rental);
        } catch (NoSuchElementException e) {
            throw new NoEntryFoundException("La location avec l'id " + rentalId + " n'existe pas.");
        }
    }

    public Rental createRental(User owner, RentalRequest rentalRequest) {
        String filePath = fileService.saveFile(rentalRequest.getPicture());
        Rental rental = new Rental(owner, rentalRequest, filePath);
        rentalRepository.save(rental);

        return rental;
    }

}
