package com.holberton.realestate.exception;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String email) {
        super("A user already exists with this email: " + email);
    }
}

