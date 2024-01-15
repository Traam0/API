package com.isla.api.dto;

import com.isla.api.model.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TokenPayload(
        @NotNull Long id, @NotBlank String version, @NotBlank Role role) {

}
