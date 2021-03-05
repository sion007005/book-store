package sion.bookstore.domain.utils.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sion.bookstore.domain.utils.ExcludeSpecialCharacter;

import java.util.regex.Pattern;

@RequiredArgsConstructor
@Component
public class PhoneNumberValidator implements Validator<String> {
    private final HasValueValidator hasValueValidator;
    private final ExcludeSpecialCharacter excludeSpecialCharacter;

    @Override
    public void validate(String phoneNumber, String type) {
        hasValueValidator.validate(phoneNumber, "phone number");

        String number = excludeSpecialCharacter.getString(phoneNumber);

        try {
            if(!Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", number)) {
                throw new ValidationException("올바르지 않은 휴대폰 입력 형식");
            }
        } catch(Exception e) {
            throw new ValidationException(e.getMessage(), e);
        }
    }
}
