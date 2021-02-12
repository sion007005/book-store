package sion.bookstore.front.controller.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sion.bookstore.domain.utils.validator.HasValueValidator;
import sion.bookstore.domain.utils.validator.NumberValidator;

@RequiredArgsConstructor
@Component
public class CartValidator {
    private final HasValueValidator hasValueValidator;
    private final NumberValidator numberValidator;


}
