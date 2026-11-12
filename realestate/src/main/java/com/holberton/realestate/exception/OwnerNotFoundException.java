package com.holberton.realestate.exception;

public class OwnerNotFoundException extends RuntimeException {
    public OwnerNotFoundException(String id) {
        super("Owner not found: " + id);
    }
}

