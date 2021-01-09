package sion.bookstore.domain.exceptions;

public class DispatcherException extends RuntimeException {

    public DispatcherException(Throwable t) {
        super(t);
    }

    public DispatcherException(String message) {
        super(message);
    }

    public DispatcherException(String message, Throwable t) {
        super(message, t);
    }

}
