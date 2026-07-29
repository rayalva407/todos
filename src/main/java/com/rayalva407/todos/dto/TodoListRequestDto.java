package com.rayalva407.todos.dto;

import jakarta.validation.constraints.NotBlank;

public record TodoListRequestDto(@NotBlank String title) {
}
