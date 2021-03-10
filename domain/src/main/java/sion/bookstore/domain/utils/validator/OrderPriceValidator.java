package sion.bookstore.domain.utils.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.service.BookService;
import sion.bookstore.domain.order.repository.OrderItem;

@Component
@RequiredArgsConstructor
public class OrderPriceValidator implements Validator<OrderItem> {
    private final BookService bookService;

    @Override
    public void validate(OrderItem item, String type) {
        Book book = bookService.findOneById(item.getBookId());

        // TODO 가격은 어떤식으로 검사해야하는가..?
        // TODO 주문 페이지에서 확인했을 때와 실제 결제할 때 가격의 변경이 있을 경우는...?
        if (item.getSalePrice() > book.getSalePrice()) {

        }
    }
}
