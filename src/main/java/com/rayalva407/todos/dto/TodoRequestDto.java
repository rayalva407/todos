package com.rayalva407.todos.dto;

import jakarta.validation.constraints.NotBlank;

public record TodoRequestDto(@NotBlank String title, String description) {}
