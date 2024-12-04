package com.rentalapp.rentalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentalapp.rentalapi.model.Rental;
import com.rentalapp.rentalapi.model.User;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
    public List<Rental> findByOwner(User owner);
}
