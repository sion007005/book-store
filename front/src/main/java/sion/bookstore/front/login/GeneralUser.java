package sion.bookstore.front.login;

import sion.bookstore.domain.auth.User;

import java.util.Objects;

public class GeneralUser implements User {
    private final Long memberId;
    private final String userEmail;
    private final String userName;
    private final String accessIp;

    private GeneralUser(Long memberId, String userEmail, String userName, String accessIp) {
        this.memberId = memberId;
        this.userEmail = userEmail;
        this.userName = userName;
        this.accessIp = accessIp;
    }

    public static GeneralUser authenticatedUser(Long memberId, String userEmail, String userName, String accessIp) {
        return new GeneralUser(memberId, userEmail, userName, accessIp);
    }

    public static GeneralUser unauthenticatedUser(String accessIp) {
        return new GeneralUser(null, null, null, accessIp);
    }

    @Override
    public boolean authenticated() {
        if (Objects.nonNull(userEmail)) {
            return true;
        }

        return false;
    }

    @Override
    public Long getMemberId() {
        return memberId;
    }

    @Override
    public String getUserEmail() {
        return userEmail;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getAccessIp() {
        return accessIp;
    }

}
