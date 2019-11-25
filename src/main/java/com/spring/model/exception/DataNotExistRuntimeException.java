package com.spring.model.exception;

public class DataNotExistRuntimeException extends RuntimeException {
    public DataNotExistRuntimeException() {
    }

    public DataNotExistRuntimeException(String message) {
        super(message);
    }

    public DataNotExistRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
