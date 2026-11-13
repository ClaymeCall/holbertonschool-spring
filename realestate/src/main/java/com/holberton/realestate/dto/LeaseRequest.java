package com.holberton.realestate.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LeaseRequest(
    @NotBlank String propertyReference,
    @NotNull Long tenantId,
    @Min(12) @Max(36) int durationMonths
) {}
