package sion.bookstore.domain.utils.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailValidator implements Validator<String> {
    @Override
    public void validate(String content, String type) {
        try {
            if(!Pattern.matches("^[_\\.0-9a-zA-Z-]+@([0-9a-zA-Z][0-9a-zA-Z-]+\\.)+[a-zA-Z]{2,6}$", content)) {
				throw new ValidationException("올바르지 않은 이메일 형식");
            }
        } catch(Exception e) {
            throw new ValidationException(e.getMessage(), e);
        }
    }
}
