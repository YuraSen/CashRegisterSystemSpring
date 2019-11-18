package springBoot.exception;

public class InvalidDataForPaginationRuntimeException extends RuntimeException {
    public InvalidDataForPaginationRuntimeException() {
    }

    public InvalidDataForPaginationRuntimeException(String message) {
        super(message);
    }

    public InvalidDataForPaginationRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
