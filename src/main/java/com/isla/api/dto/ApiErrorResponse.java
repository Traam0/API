package com.isla.api.dto;

import jakarta.validation.constraints.NotNull;

public record ApiErrorResponse(@NotNull String message) {

}
