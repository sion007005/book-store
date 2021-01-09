package sion.bookstore.domain.auth;

public class BookManagementUser implements User {

    private final Integer memberId;
    private final String userEmail;
    private final String userName;
    private final String accessIp;

    private BookManagementUser(Integer memberId, String userEmail, String userName, String accessIp) {
        this.memberId = memberId;
        this.userEmail = userEmail;
        this.userName = userName;
        this.accessIp = accessIp;
    }

    public static BookManagementUser newLoginUser(Integer memberId, String userEmail, String userName, String accessIp) {
        return new BookManagementUser(memberId, userEmail, userName, accessIp);
    }

    public static BookManagementUser newLogoutUser(String accessIp) {
        return new BookManagementUser(null, null, null, accessIp);
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
