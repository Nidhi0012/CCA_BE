package com.cybage.exception;

public class CustomConfException extends RuntimeException {
    public CustomConfException(String message) {
        super(message);
    }

    public CustomConfException(String message, Throwable cause) {
        super(message, cause);
    }
}
