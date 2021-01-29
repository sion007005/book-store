package sion.bookstore.admin.controller.util.validator;

import org.springframework.stereotype.Component;

@Component
public class StringLengthValidator {
    public void validate(String content, Integer length) {
        try {
            if (content.length() != length) {
                throw new ValidationException("올바르지 않은 문자 길이");
            }
        } catch (Exception e) {
            throw new ValidationException("문자 길이 검증 실패", e);
        }
    }
}
