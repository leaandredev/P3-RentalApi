package com.rentalapp.rentalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalapp.rentalapi.model.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

}
