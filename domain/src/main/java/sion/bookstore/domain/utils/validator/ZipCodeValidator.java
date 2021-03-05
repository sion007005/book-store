package sion.bookstore.domain.utils.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ZipCodeValidator implements Validator<Integer> {
    private final NumberValidator numberValidator;

    @Override
    public void validate(Integer zipCode, String type) {
        String stringZipCode = String.valueOf(zipCode);

        numberValidator.validate(stringZipCode, "zipcode");

        if (stringZipCode.length() != 5) {
            throw new ValidationException("우편번호 입력 올바르지 않습니다.");
        }
    }
}
