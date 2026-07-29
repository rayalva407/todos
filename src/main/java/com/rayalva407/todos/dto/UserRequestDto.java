package com.rayalva407.todos.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(
        @NotBlank(message = "Username is required")
        String username,
        @NotBlank(message = "Password is required")
        String password
) {}
