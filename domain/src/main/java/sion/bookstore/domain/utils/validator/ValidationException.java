package sion.bookstore.domain.utils.validator;

public class ValidationException extends RuntimeException {
    public ValidationException(Throwable e) {
        super(e);
    }
    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable e) {
        super(message, e);
    }
}
