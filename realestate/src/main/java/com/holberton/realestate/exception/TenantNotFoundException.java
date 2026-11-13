package com.holberton.realestate.exception;

public class TenantNotFoundException extends RuntimeException {
    public TenantNotFoundException(String id) {
        super("Tenant not found: " + id);
    }
}

