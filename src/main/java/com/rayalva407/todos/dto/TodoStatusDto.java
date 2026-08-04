package com.rayalva407.todos.dto;


import jakarta.validation.constraints.NotNull;

public record TodoStatusDto(@NotNull Boolean completed) {}
