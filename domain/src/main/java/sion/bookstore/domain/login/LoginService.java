package sion.bookstore.domain.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.member.repository.Member;
import sion.bookstore.domain.member.service.MemberService;
import sion.bookstore.domain.utils.AES256Util;
import sion.bookstore.domain.utils.SHA256Util;

import java.util.Objects;

@Slf4j
@Service
public class LoginService {

    @Autowired
    private MemberService memberService;

    //TODO Exception 처리
    public String check(String email, String plainPassword) {

        Member member = memberService.findOneByEmail(email);

        // 존재하는 회원이면
        if (Objects.nonNull(member)) {
            //해당 이메일을 가진 멤버의  암호화된 비밀번호와 salt를 같이 꺼내와서 비교한다.
            String encryptedPassword = SHA256Util.getEncrypt(plainPassword, member.getPasswordSalt());

            if (encryptedPassword.equals(member.getPassword())) {
                try {
                    String encryptedSid = getEncryptedSid(member.getId());
                    return encryptedSid;
                } catch (Exception e) {
                    throw new LoginProcessException(e.getMessage(), e);
                }
            }
        }

        throw new LoginProcessException("회원 정보에 없는 이메일 주소이거나, 비밀번호가 올바르지 않습니다.");
    }

    //TODO Exception 처리
    private String getEncryptedSid(int memberId) {

        try {
            AES256Util encryptUtil = new AES256Util();
            return encryptUtil.encrypt(String.valueOf(memberId));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new LoginProcessException(e.getMessage(), e);
        }
    }
}
