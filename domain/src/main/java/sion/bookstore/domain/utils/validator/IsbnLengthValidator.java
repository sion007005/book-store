package sion.bookstore.domain.utils.validator;

import org.springframework.stereotype.Component;

@Component
public class IsbnLengthValidator implements Validator<String> {
    public void validate(String content, String type) {
        if (type.contains("10")) {
            checkIsbnLength(content, 10);
        }

        if (type.contains("13")) {
            checkIsbnLength(content, 13);
        }
    }

    private void checkIsbnLength(String isbn, int length) {
        try {
            if (isbn.length() != length) {
                throw new ValidationException("올바르지 않은 isbn number");
            }
        } catch (Exception e) {
            throw new ValidationException("isbn number 길이 검증 실패", e);
        }
    }
}
