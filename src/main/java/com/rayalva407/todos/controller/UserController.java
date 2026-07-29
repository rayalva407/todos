package com.rayalva407.todos.controller;

import com.rayalva407.todos.dto.UserRequestDto;
import com.rayalva407.todos.dto.UserResponseDto;
import com.rayalva407.todos.model.User;
import com.rayalva407.todos.service.JwtService;
import com.rayalva407.todos.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserRequestDto createUserDto) {
        try {
            User createdUser = userService.create(createUserDto.username(), createUserDto.password());

            String token = jwtService.generateToken(createdUser.getUsername());

            ResponseCookie jwtCookie = ResponseCookie.from("accessToken", token)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(24 * 60 * 60)
                    .sameSite("Lax")
                    .build();

            UserResponseDto responseBody = new UserResponseDto(createdUser.getUsername());

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                    .body(responseBody);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
