package sion.bookstore.admin.controller.book.theme;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sion.bookstore.domain.book.theme.repository.ThemeSection;
import sion.bookstore.domain.utils.validator.HasValueValidator;
import sion.bookstore.domain.utils.validator.NumberValidator;
import sion.bookstore.domain.utils.validator.Validator;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class ThemeSectionValidator implements Validator<ThemeSection> {
    private final HasValueValidator hasValueValidator;
    private final NumberValidator numberValidator;

    @Override
    public void validate(ThemeSection themeSection, String type) {
        numberValidator.validate(String.valueOf(themeSection.getOrderNo()), "order number");
        hasValueValidator.validate(themeSection.getTitle(), "title");
        hasValueValidator.validate(themeSection.getType(), "type");

        if (Objects.nonNull(themeSection.getDescription())) {
            hasValueValidator.validate(themeSection.getDescription(), "description");
        }
    }
}
