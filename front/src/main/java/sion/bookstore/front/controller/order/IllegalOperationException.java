package sion.bookstore.front.controller.order;

public class IllegalOperationException extends RuntimeException {
    public IllegalOperationException(Throwable e) {
        super(e);
    }

    public IllegalOperationException(String message) {
        super(message);
    }

    public IllegalOperationException(String message, Throwable e) {
        super(message, e);
    }
}
