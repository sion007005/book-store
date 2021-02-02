package sion.bookstore.domain.auth;

public interface User {
    boolean authenticated();
    Integer getMemberId();
    String getUserEmail();
    String getUserName();
    String getAccessIp();
}
