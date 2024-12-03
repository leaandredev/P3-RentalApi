package com.rentalapp.rentalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentalapp.rentalapi.model.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
}
