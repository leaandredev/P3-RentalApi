package com.rentalapp.rentalapi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rentalapp.rentalapi.dto.MessageRequest;
import com.rentalapp.rentalapi.dto.OkResponse;
import com.rentalapp.rentalapi.service.MessageService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<OkResponse> sendMessage(@Valid @RequestBody MessageRequest messageRequest) {
        messageService.createMessage(messageRequest);
        return ResponseEntity.ok(new OkResponse("Message send with success"));
    }

}
