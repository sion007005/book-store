package sion.bookstore.admin;

import sion.bookstore.domain.auth.User;

public class AdminUser implements User {

    private final Integer memberId;
    private final String userEmail;
    private final String userName;
    private final String accessIp;

    private AdminUser(Integer memberId, String userEmail, String userName, String accessIp) {
        this.memberId = memberId;
        this.userEmail = userEmail;
        this.userName = userName;
        this.accessIp = accessIp;
    }

    public static AdminUser newLoginUser(Integer memberId, String userEmail, String userName, String accessIp) {
        return new AdminUser(memberId, userEmail, userName, accessIp);
    }

    public static AdminUser newLogoutUser(String accessIp) {
        return new AdminUser(null, null, null, accessIp);
    }

    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public Integer getMemberId() {
        return null;
    }

    @Override
    public String getUserEmail() {
        return null;
    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public String getAccessIp() {
        return null;
    }
}
