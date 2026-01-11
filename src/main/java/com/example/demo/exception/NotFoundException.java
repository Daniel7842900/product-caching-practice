package com.example.demo.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
    }

    public NotFoundException() {}

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }
}
