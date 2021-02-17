package sion.bookstore.domain.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sion.bookstore.domain.book.service.BookService;
import sion.bookstore.domain.cart.repository.CartItem;
import sion.bookstore.domain.utils.validator.HasValueValidator;
import sion.bookstore.domain.utils.validator.NumberValidator;
import sion.bookstore.domain.utils.validator.PlusNumberValidator;
import sion.bookstore.domain.utils.validator.Validator;

@RequiredArgsConstructor
@Component
public class CartValidator implements Validator<CartItem> {
    private final HasValueValidator hasValueValidator;
    private final NumberValidator numberValidator;
    private final BookService bookService;
    private final PlusNumberValidator plusNumberValidator;


    @Override
    public void validate(CartItem cartItem, String type) {
        // TODO Check 이렇게 받아온 아이디가 숫자인지를 확인? 아니면 실제로 존재하는 멤버/책인지를 확인?
        numberValidator.validate(String.valueOf(cartItem.getMemberId()), "member Id");
        numberValidator.validate(String.valueOf(cartItem.getMemberId()), "book Id");
        plusNumberValidator.validate(cartItem.getQuantity(), "quantity");
    }
}
