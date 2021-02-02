package sion.bookstore.domain.login;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException(Throwable e) {
        super(e);
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable e) {
        super(message, e);
    }

}
