package sion.bookstore.domain.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.service.BookService;
import sion.bookstore.domain.order.repository.Order;
import sion.bookstore.domain.order.repository.OrderItem;
import sion.bookstore.domain.utils.validator.*;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderValidator implements Validator<Order> {
    private final BookService bookService;
    private final SaleStatusValidator saleStatusValidator;
    private final ZipCodeValidator zipCodeValidator;
    private final PhoneNumberValidator phoneNumberValidator;
    private final OrderPriceValidator orderPriceValidator;

    @Override
    public void validate(Order order, String type) {
        zipCodeValidator.validate(order.getZipCode(), "zip code");
        phoneNumberValidator.validate(order.getPhone(), "phone number");

        List<OrderItem> items = order.getItems();

        for (OrderItem item : items) {
            Book book = bookService.findOneById(item.getBookId());
            saleStatusValidator.validate(book.getStatus(), "book sale status");
            orderPriceValidator.validate(item, "order price");
        }
    }
}
