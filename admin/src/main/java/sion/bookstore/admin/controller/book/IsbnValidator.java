package sion.bookstore.admin.controller.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sion.bookstore.domain.utils.validator.NumberValidator;
import sion.bookstore.domain.utils.validator.StringLengthValidator;
import sion.bookstore.domain.utils.validator.ValidationException;
import sion.bookstore.domain.utils.validator.Validator;

import java.util.List;

/**
 * isbn 번호가 숫자로만 이루어져 있는지와 자릿수를 검증
 * isbn10과 isbn13 중 하나의 번호만 정상적으로 등록되어있다면 통과
 */
@RequiredArgsConstructor
@Component
public class IsbnValidator implements Validator<List<String>> {
    private final NumberValidator numberValidator;
    private final StringLengthValidator stringLengthValidator;

    public void validate(List<String> isbns, String type) {
        Boolean isbn10Validated = checkNumberAndLength(isbns.get(0), 10);
        Boolean isbn13Validated = checkNumberAndLength(isbns.get(1), 13);

        if (isbn10Validated == false && isbn13Validated == false) {
            throw new ValidationException("isbn 번호가 유효하지 않습니다.");
        }
    }

    private Boolean checkNumberAndLength(String isbn, int length) {
        try {
            numberValidator.validate(isbn, "isbn" + length);
        } catch (Exception e) {
            return false;
        }

        try {
            stringLengthValidator.validate(isbn, length);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
