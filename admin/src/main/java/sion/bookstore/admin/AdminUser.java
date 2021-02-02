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
    //TODO CHECK 어드민 유저임을 이메일 주소로 한번 더 확인
    public boolean authenticated() {
        if (Objects.isNull(userEmail)) {
            return false;
        }

        int index = userEmail.indexOf("@");
        String emailAddress = userEmail.substring(index + 1);
        if (emailAddress == "sionstore.com") {
            return true;
        }

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
