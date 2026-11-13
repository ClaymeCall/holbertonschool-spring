package com.holberton.realestate.dto;

import com.holberton.realestate.model.Tenant;

public class TenantMapper {

    private TenantMapper() {}

    public static TenantResponse toResponse(Tenant t) {
        return new TenantResponse(
            t.getId(),
            t.getName(),
            t.getEmail(),
            t.getMonthlyIncome()
        );
    }

    public static Tenant toEntity(TenantRequest r) {
        return new Tenant(
            r.name(),
            r.email(),
            r.monthlyIncome()
        );
    }
}
