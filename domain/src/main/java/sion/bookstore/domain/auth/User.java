package sion.bookstore.domain.auth;

public interface User {
    boolean authenticated();
    Long getMemberId();
    String getUserEmail();
    String getUserName();
    String getAccessIp();
}
