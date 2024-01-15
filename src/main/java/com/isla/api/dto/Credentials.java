package com.isla.api.dto;

import jakarta.validation.constraints.NotBlank;

public record Credentials(@NotBlank(message = "username required") String username,
        @NotBlank(message = "password required") String password) {

}
