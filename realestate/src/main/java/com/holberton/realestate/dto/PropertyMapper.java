package com.holberton.realestate.dto;

import com.holberton.realestate.model.Property;

public class PropertyMapper {

    private PropertyMapper() {}

    public static PropertyResponse toResponse(Property p) {
        return new PropertyResponse(
            p.getReference(),
            p.getAddress(),
            p.getSurface(),
            p.getMonthlyRent(),
            p.getEnergyClass(),
            p.getMonthlyRent() * 12,
            p.getOwner() == null ? null : p.getOwner().getId(),
            p.isRented()
        );
    }

    public static Property toEntity(PropertyRequest r) {
        return new Property(
            r.reference(),
            r.address(),
            r.surface(),
            r.monthlyRent(),
            r.energyClass()
        );
    }
}
