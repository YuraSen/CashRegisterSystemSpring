package springBoot.exception;

public class UserExistRuntimeException extends RuntimeException{
    public UserExistRuntimeException() {
    }

    public UserExistRuntimeException(String message) {
        super(message);
    }

    public UserExistRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
