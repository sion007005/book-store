package sion.bookstore.domain.exceptions;

public class LoginProcessException extends RuntimeException {

    public LoginProcessException(Throwable e) {
        super(e);
    }

    public LoginProcessException(String message) {
        super(message);
    }

    public LoginProcessException(String message, Throwable e) {
        super(message, e);
    }

}
