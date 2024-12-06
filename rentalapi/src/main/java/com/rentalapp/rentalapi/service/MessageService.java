package com.rentalapp.rentalapi.service;

import org.springframework.stereotype.Service;
import com.rentalapp.rentalapi.model.Message;
import com.rentalapp.rentalapi.repository.MessageRepository;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message saveMessage(Message message) {
        messageRepository.save(message);
        return message;
    }

}
