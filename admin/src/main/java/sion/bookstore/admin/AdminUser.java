package sion.bookstore.admin;

import sion.bookstore.domain.auth.User;
import sion.bookstore.domain.member.repository.Member;

import java.util.Objects;

public class AdminUser implements User {
    private final Long memberId;
    private final String userEmail;
    private final String userName;
    private final String accessIp;
    private final Member member;

    private AdminUser(Long memberId, String userEmail, String userName, Member member, String accessIp) {
        this.memberId = memberId;
        this.userEmail = userEmail;
        this.userName = userName;
        this.member = member;
        this.accessIp = accessIp;
    }

    public static AdminUser authenticatedUser(Member member, String accessIp) {
        return new AdminUser(member.getId(), member.getEmail(), member.getName(), member, accessIp);
    }

    public static AdminUser unauthenticatedUser(String accessIp) {
        return new AdminUser(null, null, null, null, accessIp);
    }

    @Override
    public boolean authenticated() {
        if (Objects.isNull(userEmail)) {
            return false;
        }

        if (!member.isAdmin()) {
            return false;
        }

        return true;
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
