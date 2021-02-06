package sion.bookstore.domain.login;

import sion.bookstore.domain.member.repository.Member;
import sion.bookstore.domain.utils.AES256Util;
import sion.bookstore.domain.utils.SHA256Util;

public interface LoginService {
     String checkLoginMember(String email, String inputPassword);

    default void comparePassword(Member member, String inputPassword) {
        String encryptedPassword = SHA256Util.getEncrypt(inputPassword, member.getPasswordSalt());

        if (!encryptedPassword.equals(member.getPassword())) {
            throw new AuthenticationException("비밀번호가 일치하지 않습니다.");
        }
    }

    default String getEncryptedSid(Long memberId) {
        try {
            AES256Util encryptUtil = new AES256Util();
            return encryptUtil.encrypt(String.valueOf(memberId));
        } catch (Exception e) {
            throw new AuthenticationException(e.getMessage(), e);
        }
    }
}
