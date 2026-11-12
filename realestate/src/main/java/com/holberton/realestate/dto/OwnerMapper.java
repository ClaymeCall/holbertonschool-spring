package com.holberton.realestate.dto;

import com.holberton.realestate.model.Owner;

public class OwnerMapper {

    private OwnerMapper() {}

    public static OwnerResponse toResponse(Owner o) {
        return new OwnerResponse(
            o.getId(),
            o.getName(),
            o.getEmail()
        );
    }

    public static Owner toEntity(OwnerRequest r) {
        return new Owner(
            r.name(),
            r.email()
        );
    }
}
