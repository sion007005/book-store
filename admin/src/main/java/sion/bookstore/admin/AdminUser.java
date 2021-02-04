package sion.bookstore.admin;

import sion.bookstore.domain.auth.User;

import java.util.Objects;

public class AdminUser implements User {

    private final Long memberId;
    private final String userEmail;
    private final String userName;
    private final String accessIp;

    private AdminUser(Long memberId, String userEmail, String userName, String accessIp) {
        this.memberId = memberId;
        this.userEmail = userEmail;
        this.userName = userName;
        this.accessIp = accessIp;
    }

    public static AdminUser authenticatedUser(Long memberId, String userEmail, String userName, String accessIp) {
        return new AdminUser(memberId, userEmail, userName, accessIp);
    }

    public static AdminUser unauthenticatedUser(String accessIp) {
        return new AdminUser(null, null, null, accessIp);
    }

    @Override
    //TODO CHECK member column에 admin 추가해서 true/false로 관리자 구분
    public boolean authenticated() {
        if (Objects.isNull(userEmail)) {
            return false;
        }

        return false;
    }

    @Override
    public Long getMemberId() {
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
