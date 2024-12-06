package com.rentalapp.rentalapi.service;

import org.springframework.stereotype.Service;

import com.rentalapp.rentalapi.dto.request.MessageRequest;
import com.rentalapp.rentalapi.mapper.MessageMapper;
import com.rentalapp.rentalapi.model.Message;
import com.rentalapp.rentalapi.repository.MessageRepository;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    public MessageService(MessageRepository messageRepository, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    public Message createMessage(MessageRequest messageRequest) {
        Message message = messageMapper.requestToEntity(messageRequest);
        messageRepository.save(message);
        return message;
    }

}
