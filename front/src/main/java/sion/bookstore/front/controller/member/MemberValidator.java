package sion.bookstore.front.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sion.bookstore.domain.member.repository.Member;
import sion.bookstore.domain.utils.validator.*;

@RequiredArgsConstructor
@Component
public class MemberValidator implements Validator<Member> {
    private final EngKorStringValidator engKorStringValidator;
    private final HasValueValidator hasValueValidator;
    private final PhoneNumberValidator phoneNumberValidator;
    private final EmailValidator emailValidator;

    @Override
    public void validate(Member member, String type) {
        engKorStringValidator.validate(member.getName(), "name");
        hasValueValidator.validate(member.getPassword(), "password");
        phoneNumberValidator.validate(member.getPhone(), "phone number");
        emailValidator.validate(member.getEmail(), "email");
    }
}
