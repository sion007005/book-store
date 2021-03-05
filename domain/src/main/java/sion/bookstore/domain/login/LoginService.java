package sion.bookstore.domain.login;

import sion.bookstore.domain.member.repository.Member;
import sion.bookstore.domain.utils.AES256Util;
import sion.bookstore.domain.utils.SHA256Util;

public interface LoginService {
    String COOKIE_VALUE_SEPERATOR = ":-:";

    Member checkMemberAndPassword(String email, String inputPassword);

    default void comparePassword(Member member, String inputPassword) {
        String encryptedPassword = SHA256Util.getEncrypt(inputPassword, member.getPasswordSalt());

        if (!encryptedPassword.equals(member.getPassword())) {
            throw new AuthenticationException("비밀번호가 일치하지 않습니다.");
        }
    }

    default String getEncryptedCookieValue(Member member) {
        try {
            AES256Util encryptUtil = new AES256Util();
            String cookieValue = member.getEmail() + COOKIE_VALUE_SEPERATOR + member.getId() + COOKIE_VALUE_SEPERATOR + member.getPasswordSalt();

            return encryptUtil.encrypt(cookieValue);
        } catch (Exception e) {
            throw new AuthenticationException(e.getMessage(), e);
        }
    }
}
