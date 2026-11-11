package com.holberton.realestate.dto;

import com.holberton.realestate.model.Address;
import com.holberton.realestate.model.EnergyClass;
import jakarta.validation.constraints.*;
import jakarta.validation.Valid;


public record PropertyRequest(
    @NotNull @Pattern(regexp = "P-\\d{3}") String reference,
    @NotNull @Valid Address address,
    @Positive int surface,
    @PositiveOrZero int monthlyRent,
    @NotNull EnergyClass energyClass
) {}
