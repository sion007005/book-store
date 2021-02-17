package sion.bookstore.front.controller.member;

public class InvalidFileExtensionException extends RuntimeException {
    public InvalidFileExtensionException(Throwable e) {
        super(e);
    }

    public InvalidFileExtensionException(String message) {
        super(message);
    }

    public InvalidFileExtensionException(String message, Throwable e) {
        super(message, e);
    }
}
