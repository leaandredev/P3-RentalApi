package com.rentalapp.rentalapi.mapper;

import org.springframework.stereotype.Component;

import com.rentalapp.rentalapi.dto.request.MessageRequest;
import com.rentalapp.rentalapi.model.Message;
import com.rentalapp.rentalapi.repository.RentalRepository;
import com.rentalapp.rentalapi.repository.UserRepository;

@Component
public class MessageMapper {

    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;

    public MessageMapper(RentalRepository rentalRepository, UserRepository userRepository) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
    }

    public Message requestToEntity(MessageRequest request) {
        return new Message(
                null,
                rentalRepository.findById(request.getRental_id()).get(),
                userRepository.findById(request.getUser_id()).get(),
                request.getMessage(),
                null,
                null);

    }
}