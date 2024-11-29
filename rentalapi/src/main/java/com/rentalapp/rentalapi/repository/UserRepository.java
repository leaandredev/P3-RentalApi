package com.rentalapp.rentalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentalapp.rentalapi.model.DbUser;

@Repository
public interface UserRepository extends JpaRepository<DbUser, Integer> {
    public DbUser findByEmail(String email);
}
