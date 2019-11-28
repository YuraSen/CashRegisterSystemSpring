package com.spring.model.exception;

public class InvalidIdRuntimeException extends RuntimeException {
    public InvalidIdRuntimeException() {
    }

    public InvalidIdRuntimeException(String message) {
        super(message);
    }

    public InvalidIdRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
