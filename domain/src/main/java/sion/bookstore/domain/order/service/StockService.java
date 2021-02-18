package sion.bookstore.domain.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.book.service.BookService;
import sion.bookstore.domain.order.repository.OrderItem;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {
    public static final Integer PLUS_SIGN_NUMBER = 1;
    public static final Integer MINUS_SIGN_NUMBER = -1;

    private final BookService bookService;

    public void changeStockQuantity(List<OrderItem> items, Integer signNumber) {
        for (OrderItem item : items) {
            bookService.changeStockQuantity(item.getBookId(), item.getQuantity() * signNumber);
        }
    }
}
