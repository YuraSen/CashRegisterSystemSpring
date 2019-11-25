package com.spring.model.exception;

public class IdInvalidRuntimeException extends RuntimeException {
    public IdInvalidRuntimeException() {
    }

    public IdInvalidRuntimeException(String message) {
        super(message);
    }

    public IdInvalidRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
