package com.holberton.realestate.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

public record OwnerRequest(
    @NotBlank String name,
    @NotBlank @Email String email
) {}
