package com.rentalapp.rentalapi.service;

import org.springframework.stereotype.Service;

import com.rentalapp.rentalapi.dto.MessageRequest;
import com.rentalapp.rentalapi.model.Message;
import com.rentalapp.rentalapi.model.Rental;
import com.rentalapp.rentalapi.model.User;
import com.rentalapp.rentalapi.repository.MessageRepository;
import com.rentalapp.rentalapi.repository.RentalRepository;
import com.rentalapp.rentalapi.repository.UserRepository;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository,
            RentalRepository rentalRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
    }

    public Message createMessage(MessageRequest messageRequest) {
        Rental rental = rentalRepository.findById(messageRequest.getRental_id()).get();
        User user = userRepository.findById(messageRequest.getUser_id()).get();
        Message message = new Message(messageRequest.getMessage(), rental, user);
        messageRepository.save(message);
        return message;
    }

}
