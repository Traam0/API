package com.isla.api.model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record User(Integer id, @NotBlank String username, @Email String email, @NotBlank String password,
        LocalDateTime createdAt, LocalDateTime updatedAt,
        @NotBlank Role role,
        @NotBlank Status state) {
}
