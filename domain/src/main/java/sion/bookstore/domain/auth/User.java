package sion.bookstore.domain.auth;

public interface User {
    boolean isLogin();
    Integer getMemberId();
    String getUserEmail();
    String getUserName();
    String getAccessIp();
}
