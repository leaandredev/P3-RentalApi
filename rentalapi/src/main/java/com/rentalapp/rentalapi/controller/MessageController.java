package com.rentalapp.rentalapi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rentalapp.rentalapi.dto.request.MessageRequest;
import com.rentalapp.rentalapi.dto.response.ErrorResponse;
import com.rentalapp.rentalapi.dto.response.OkResponse;
import com.rentalapp.rentalapi.service.MessageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(description = "Envoi d'un message pour l'utilisateur connectéa un propriétaire")
    @ApiResponse(responseCode = "200", description = "Message envoyé", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OkResponse.class)))
    @ApiResponse(responseCode = "401", description = "Échec de l'authentification", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @PostMapping
    public ResponseEntity<OkResponse> sendMessage(@Valid @RequestBody MessageRequest messageRequest) {
        messageService.createMessage(messageRequest);
        return ResponseEntity.ok(new OkResponse("Message send with success"));
    }

}
