package com.rentalapp.rentalapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rentalapp.rentalapi.dto.response.UserResponse;
import com.rentalapp.rentalapi.mapper.UserMapper;
import com.rentalapp.rentalapi.model.User;
import com.rentalapp.rentalapi.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Operation(description = "Récupération des informations de propriétaire")
    @ApiResponse(responseCode = "200", description = "Propriétaire récupéré", content = @Content(schema = @Schema(implementation = UserResponse.class)))
    @ApiResponse(responseCode = "401", description = "Connexion non autorisé")
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
        User authenticatedUser = userService.getUserById(Integer.valueOf(id));
        return ResponseEntity.ok(userMapper.entityToResponse(authenticatedUser));
    }

}
