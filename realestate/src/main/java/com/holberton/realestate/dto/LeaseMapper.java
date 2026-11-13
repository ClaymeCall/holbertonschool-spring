package com.holberton.realestate.dto;

import com.holberton.realestate.model.Lease;

public class LeaseMapper {

    public static final int DEPOSIT_MONTHS = 2;

    private LeaseMapper() {}

    public static LeaseResponse toResponse(Lease l) {
        return new LeaseResponse(
            l.getId(),
            l.getProperty().getReference(),
            l.getTenant().getId(),
            l.getDurationMonths(),
            l.getMonthlyRent(),
            l.getMonthlyRent() * l.getDurationMonths(),
            l.getMonthlyRent() * DEPOSIT_MONTHS,
            l.isActive()
        );
    }
}
