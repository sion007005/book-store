package sion.bookstore.domain.utils.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * 맨 앞글자가 공백이 아닌 문자로 시작하면
 * 값을 가지고 있다고 판단한다. (특수문자를 포함한 모든 문자값)
 */
@Component
public class HasValueValidator {
    public boolean validate(String content, String type) {
        try {
            String trimedContent = content.trim();
            if(!Pattern.matches("^\\S+", trimedContent)) {
				throw new ValidationException(type +": 입력된 값이 없거나 올바르지 않음(공백주의)");
            }
        } catch (Exception e) {
            throw new ValidationException(e.getMessage(), e);
        }
        return true;
    }
}
