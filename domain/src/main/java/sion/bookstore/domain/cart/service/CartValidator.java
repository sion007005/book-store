package sion.bookstore.domain.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.service.BookService;
import sion.bookstore.domain.cart.repository.CartItem;
import sion.bookstore.domain.utils.validator.PositiveIntegerValidator;
import sion.bookstore.domain.utils.validator.SaleStatusValidator;
import sion.bookstore.domain.utils.validator.Validator;

@RequiredArgsConstructor
@Component
public class CartValidator implements Validator<CartItem> {
    private final PositiveIntegerValidator positiveIntegerValidator;
    private final SaleStatusValidator saleStatusValidator;
    private final BookService bookService;

    @Override
    public void validate(CartItem cartItem, String type) {
        positiveIntegerValidator.validate(cartItem.getQuantity(), "quantity");

        Book book = bookService.findOneById(cartItem.getBookId());
        saleStatusValidator.validate(book.getStatus(), "sale status");
    }
}
