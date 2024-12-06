package com.rentalapp.rentalapi.service;

import org.springframework.stereotype.Service;
import com.rentalapp.rentalapi.model.Message;
import com.rentalapp.rentalapi.repository.MessageRepository;

/* Service for Message treatment */
@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Saving the message send by a user to another rental owner
     * 
     * @param message The message send by a user
     * @return the message that has been saved in database
     */
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

}
