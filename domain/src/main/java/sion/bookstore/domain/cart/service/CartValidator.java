package sion.bookstore.domain.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sion.bookstore.domain.cart.repository.CartItem;
import sion.bookstore.domain.utils.validator.PlusNumberValidator;
import sion.bookstore.domain.utils.validator.Validator;

@RequiredArgsConstructor
@Component
public class CartValidator implements Validator<CartItem> {
    private final PlusNumberValidator plusNumberValidator;


    @Override
    public void validate(CartItem cartItem, String type) {
        plusNumberValidator.validate(cartItem.getQuantity(), "quantity");
    }
}
