package sion.bookstore.admin.controller.book.thema;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sion.bookstore.domain.book.thema.repository.ThemaSection;
import sion.bookstore.domain.utils.validator.HasValueValidator;
import sion.bookstore.domain.utils.validator.NumberValidator;
import sion.bookstore.domain.utils.validator.Validator;

@RequiredArgsConstructor
@Component
public class ThemaSectionValidator implements Validator<ThemaSection> {
    private final HasValueValidator hasValueValidator;
    private final NumberValidator numberValidator;

    @Override
    public void validate(ThemaSection themaSection, String type) {
        numberValidator.validate(String.valueOf(themaSection.getOrderNo()), "order number");
        hasValueValidator.validate(themaSection.getTitle(), "title");
        hasValueValidator.validate(themaSection.getType(), "type");
    }
}
