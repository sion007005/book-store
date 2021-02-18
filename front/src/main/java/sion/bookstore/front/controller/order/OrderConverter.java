package sion.bookstore.front.controller.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.service.BookService;
import sion.bookstore.domain.order.repository.Order;
import sion.bookstore.domain.order.repository.OrderItem;
import sion.bookstore.domain.order.repository.OrderItemForm;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderConverter {
    private final BookService bookService;

    public List<OrderItemForm> convertToOrderItemForm(List<CartForm.CartItemForm> cartItemForms) {
        List<OrderItemForm> orderItemList = new ArrayList<>();
        for (CartForm.CartItemForm cartItemForm : cartItemForms) {
            OrderItemForm orderItem = new OrderItemForm();

            orderItem.setBookId(cartItemForm.getBookId());
            orderItem.setQuantity(cartItemForm.getQuantity());
            setPriceAndAdditionalInfo(cartItemForm.getBookId(), orderItem);

            orderItemList.add(orderItem);
        }

        return orderItemList;
    }

    public Order convertToOrder(OrderForm orderForm) {
        Order order = new Order();

        List<OrderItem> orderItems = new ArrayList<>();
        List<OrderItemForm> orderItemForms = orderForm.getOrderItemForms();
        for (OrderItemForm orderItemForm : orderItemForms) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBookId(orderItemForm.getBookId());
            orderItem.setBookTitle(orderItemForm.getTitle());
            orderItem.setCoverImagePath(orderItemForm.getCoverImagePath());
            orderItem.setQuantity(orderItemForm.getQuantity());
            orderItem.setSalePrice(getSalePrice(orderItemForm.getBookId()));

            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        order.setPaymentType(orderForm.getPaymentType());
        order.setAddressBasic(orderForm.getAddressBasic());
        order.setAddressDetail(orderForm.getAddressDetail());
        order.setZipCode(orderForm.getZipCode());
        order.setPhone(orderForm.getPhone());
        order.setMessage(orderForm.getMessage());

        return order;
    }

    private Integer getSalePrice(Long bookId) {
        Book book = bookService.findOneById(bookId);
        return book.getSalePrice();
    }

    private void setPriceAndAdditionalInfo(Long bookId, OrderItemForm orderItem) {
        Book book = bookService.findOneById(bookId);

        orderItem.setSalePrice(book.getSalePrice());
        orderItem.setTitle(book.getTitle());
        orderItem.setCoverImagePath(book.getCoverImagePath());
    }
}
