package sion.bookstore.domain.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.member.repository.Member;
import sion.bookstore.domain.member.service.MemberService;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminLoginService implements LoginService {
    private final MemberService memberService;

    public Member checkMemberAndPassword(String email, String inputPassword) {
        Member member = memberService.findOneByEmail(email);

        if (Objects.isNull(member) || !member.isAdmin()) {
            throw new AuthenticationException("가입되지 않은 않았거나 관리자 권한이 없습니다.");
        }

        comparePassword(member, inputPassword);

        return member;
    }
}
