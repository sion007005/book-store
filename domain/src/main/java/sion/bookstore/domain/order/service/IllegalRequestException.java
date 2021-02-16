package sion.bookstore.domain.order.service;

public class IllegalRequestException extends RuntimeException {
    public IllegalRequestException(Throwable e) {
        super(e);
    }

    public IllegalRequestException(String message) {
        super(message);
    }

    public IllegalRequestException(String message, Throwable e) {
        super(message, e);
    }
}
