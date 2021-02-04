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
public class GeneralLoginService implements LoginService {
    private final MemberService memberService;

    public Member findExistingMember(String email) {
        Member member = memberService.findOneByEmail(email);

        if (Objects.isNull(member)) {
            throw new AuthenticationException("가입되지 않은 사용자입니다.");
        }

        return member;
    }
}
