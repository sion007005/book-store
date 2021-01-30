package sion.bookstore.domain.utils.validator;

import org.springframework.stereotype.Component;

@Component
public class NumberValidator implements Validator<String> {
    @Override
    public void validate(String content, String type) {
        for (int i = 0; i < content.length(); i++) {
            try {
                if (!Character.isDigit(content.charAt(i))) {
                    throw new ValidationException("올바르지 않은 숫자 입력");
                }
            } catch (Exception e) {
                throw new ValidationException("숫자 검증 중 Exception 발생", e);
            }
        }
    }
}
