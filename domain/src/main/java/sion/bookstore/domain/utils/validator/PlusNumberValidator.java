package sion.bookstore.domain.utils.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PlusNumberValidator implements Validator<Integer> {
    private final HasValueValidator hasValueValidator;

    @Override
    public void validate(Integer content, String type) {
        hasValueValidator.validate(String.valueOf(content), "number");

        try {
            if(content < 0) {
                throw new ValidationException("올바르지 않은 숫자 입력");
            }
        } catch(Exception e) {
            throw new ValidationException("올바르지 않은 숫자 입력", e);
        }
    }
}
