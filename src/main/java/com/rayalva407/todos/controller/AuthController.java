package com.rayalva407.todos.controller;

import com.rayalva407.todos.dto.UserResponseDto;
import com.rayalva407.todos.model.User;
import com.rayalva407.todos.service.AuthService;
import com.rayalva407.todos.service.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser, @CookieValue(name = "accessToken", required = false) String existingToken) {
        if (existingToken != null) {
            return new ResponseEntity<>("You are already logged in", HttpStatus.BAD_REQUEST);
        }

        try {
            User authenticatedUser = authService.authenticate(loginUser);

            String loginToken = jwtService.generateToken(authenticatedUser.getUsername());

            ResponseCookie jwtCookie = ResponseCookie.from("accessToken", loginToken)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(24 * 60 * 60)
                    .sameSite("Lax")
                    .build();

            UserResponseDto responseDto = new UserResponseDto(authenticatedUser.getUsername());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                    .body(responseDto);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        ResponseCookie logoutCookie = ResponseCookie.from("accessToken", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, logoutCookie.toString())
                .body("You are now logged out");
    }


}
