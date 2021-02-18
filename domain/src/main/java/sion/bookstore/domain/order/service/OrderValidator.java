package sion.bookstore.domain.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sion.bookstore.domain.book.service.BookService;
import sion.bookstore.domain.order.repository.Order;
import sion.bookstore.domain.utils.validator.SaleStatusValidator;
import sion.bookstore.domain.utils.validator.Validator;

@Component
@RequiredArgsConstructor
public class OrderValidator implements Validator<Order> {
    private final SaleStatusValidator saleStatusValidator;
    private final BookService bookService;

    @Override
    public void validate(Order content, String type) {
        //TODO
    }
}
