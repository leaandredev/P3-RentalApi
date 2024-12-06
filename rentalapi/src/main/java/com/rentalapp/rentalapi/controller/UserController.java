package com.rentalapp.rentalapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rentalapp.rentalapi.documentation.UserControllerDocumentation;
import com.rentalapp.rentalapi.dto.response.UserResponse;
import com.rentalapp.rentalapi.mapper.UserMapper;
import com.rentalapp.rentalapi.model.User;
import com.rentalapp.rentalapi.service.UserService;

/** Endpoints related to user informations */
@RestController
@RequestMapping("/api/user")
public class UserController implements UserControllerDocumentation {

    private final UserService userService;
    private final UserMapper userMapper;

    UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
        User authenticatedUser = userService.getUserById(Integer.valueOf(id));
        return ResponseEntity.ok(userMapper.entityToResponse(authenticatedUser));
    }

}
