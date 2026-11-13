package com.holberton.realestate.exception;

public class InsufficientIncomeException extends RuntimeException {
    public InsufficientIncomeException(Long tenantId, int income, int required) {
        super("Tenant " + tenantId + " earns " + income + " but needs at least " + required);
    }
}
