package sion.bookstore.domain.utils.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * 한글이나 영어로만 이루어진 문자열(한글 + 영어 X, 한글만 혹은 영어만)
 */
@RequiredArgsConstructor
@Component
public class EngKorStringValidator implements Validator<String> {
    private final HasValueValidator hasValueValidator;

    public void validate(String content, String nameType) {
        hasValueValidator.validate(content, nameType);

        try {
            if(!Pattern.matches("^[가-힣]+$|^[a-zA-Z\\s]+$", content)) {
                throw new ValidationException("올바르지 않은 이름 입력");
            }
        } catch(Exception e) {
            throw new ValidationException(nameType + ": 올바르지 않은 이름 형태", e);
        }
    }
}
