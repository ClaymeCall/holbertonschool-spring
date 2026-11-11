package com.holberton.realestate.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Embeddable
public record Address(
    @Column(nullable = false) @NotBlank String street,
    @Column(nullable = false) @NotNull @Pattern(regexp = "\\d{5}") String zipCode,
    @Column(nullable = false) @NotBlank String city
) {}
