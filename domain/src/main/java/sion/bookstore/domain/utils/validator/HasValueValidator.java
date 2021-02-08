package sion.bookstore.domain.utils.validator;

import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 맨 앞글자가 공백이 아닌 문자로 시작하면
 * 값을 가지고 있다고 판단한다. (특수문자를 포함한 모든 문자값)
 */
@Component
public class HasValueValidator {
    public boolean validate(String content, String type) {
        if (Objects.isNull(content)) {
            throw new ValidationException(type +": 입력된 값이 없거나 올바르지 않음(공백주의)");
        }

        if (content.equals("") || content.equals(" ")) {
            throw new ValidationException(type +": 입력된 값이 없거나 올바르지 않음(공백주의)");
        }

        if (content.startsWith(" ")) {
            throw new ValidationException(type +": 입력된 값이 없거나 올바르지 않음(공백주의)");
        }

        return true;
    }
}
