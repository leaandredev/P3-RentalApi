package com.rentalapp.rentalapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rentalapp.rentalapi.documentation.MessageControllerDocumentation;
import com.rentalapp.rentalapi.dto.request.MessageRequest;
import com.rentalapp.rentalapi.dto.response.OkResponse;
import com.rentalapp.rentalapi.mapper.MessageMapper;
import com.rentalapp.rentalapi.model.Message;
import com.rentalapp.rentalapi.service.MessageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/messages")
public class MessageController implements MessageControllerDocumentation {

    private final MessageService messageService;
    private final MessageMapper messageMapper;

    MessageController(MessageService messageService, MessageMapper messageMapper) {
        this.messageService = messageService;
        this.messageMapper = messageMapper;
    }

    @Override
    @PostMapping
    @ResponseBody
    public ResponseEntity<OkResponse> sendMessage(@Valid @RequestBody MessageRequest messageRequest) {
        Message message = messageMapper.requestToEntity(messageRequest);
        messageService.saveMessage(message);
        return ResponseEntity.ok(new OkResponse("Message send with success"));
    }

}
