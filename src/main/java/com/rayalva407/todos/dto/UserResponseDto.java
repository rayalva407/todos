package com.rayalva407.todos.dto;

import jakarta.validation.constraints.NotNull;

public record UserResponseDto(@NotNull String username) {
}
