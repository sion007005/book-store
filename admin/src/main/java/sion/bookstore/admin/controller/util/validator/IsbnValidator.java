package sion.bookstore.admin.controller.util.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * isbn 번호가 숫자로만 이루어져 있는지와 자릿수를 검증
 * isbn10과 isbn13 중 하나의 번호만 정상적으로 등록되어있다면 통과
 */
@RequiredArgsConstructor
@Component
public class IsbnValidator {
    private final NumberValidator numberValidator;
    private final StringLengthValidator stringLengthValidator;
    private int exceptionCount;

    public void validate(String isbn10, String isbn13) {
        checkNumberAndLength(isbn10, 10);
        checkNumberAndLength(isbn13, 13);

        if (exceptionCount > 3) {
            throw new ValidationException("isbn 번호가 유효하지 않습니다.");
        }
    }

    private void checkNumberAndLength(String isbn, int length) {
        try {
            numberValidator.validate(isbn);
        } catch (Exception e) {
            exceptionCount++;
        }

        try {
            stringLengthValidator.validate(isbn, length);
        } catch (Exception e) {
            exceptionCount++;
        }
    }
}
