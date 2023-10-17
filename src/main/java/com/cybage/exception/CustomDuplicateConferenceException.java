package com.cybage.exception;

public class CustomDuplicateConferenceException extends RuntimeException {
    public CustomDuplicateConferenceException(String message) {
        super(message);
    }

    public CustomDuplicateConferenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
