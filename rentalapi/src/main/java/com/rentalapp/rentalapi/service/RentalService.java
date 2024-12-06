package com.rentalapp.rentalapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rentalapp.rentalapi.dto.request.RentalRequest;
import com.rentalapp.rentalapi.exception.NoEntryFoundException;
import com.rentalapp.rentalapi.mapper.RentalMapper;
import com.rentalapp.rentalapi.model.Rental;
import com.rentalapp.rentalapi.repository.RentalRepository;

import lombok.extern.slf4j.Slf4j;

/* Service for Rental treatment. Handle get, create and update rental actions */
@Service
@Slf4j
public class RentalService {

    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;

    public RentalService(RentalRepository rentalRepository, RentalMapper rentalMapper) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
    }

    /**
     * Get a list of all rentals
     * 
     * @return list of all rentals
     */
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    /**
     * Get a rental by his id
     * 
     * @param rentalId The id of the rental
     * @return The rental entity found
     */
    public Rental getRentalById(Integer rentalId) {
        return rentalRepository.findById(rentalId)
                .orElseThrow(() -> new NoEntryFoundException("This rental does not exist"));
    }

    /**
     * Save a rental in database
     * 
     * @param rental The rental created or updated
     * @return The rental entity saved
     */
    public Rental saveRental(Rental rental) {
        return rentalRepository.save(rental);
    }

    /**
     * Update the Rental entity from rentalRequest fields and save in database
     * 
     * @param rentalId      Then rental id that need to be update
     * @param rentalRequest The rental request containing modifications
     * @return The rental entity updated and saved in database
     */
    public Rental updateRental(Integer rentalId, RentalRequest rentalRequest) {
        Rental rental = getRentalById(rentalId);
        rentalMapper.updateEntityFromRequest(rental, rentalRequest);
        return saveRental(rental);
    }

}
