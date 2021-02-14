package sion.bookstore.domain.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sion.bookstore.domain.auth.UserContext;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.service.BookService;
import sion.bookstore.domain.cart.service.CartService;
import sion.bookstore.domain.order.repository.Order;
import sion.bookstore.domain.order.repository.OrderItem;
import sion.bookstore.domain.order.repository.OrderRepository;
import sion.bookstore.domain.order.repository.OrderStatus;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final CartService cartService;
    private final BookService bookService;

    public Long create(Order order, List<Long> cartItemIds) {
        Order createdOrder = createOrder(order);
        createOrderItems(createdOrder, order.getItems());
        cartService.removeByItemIds(cartItemIds);
        return createdOrder.getId();
    }

    private Order createOrder(Order order) {
        // TODO
        //  1. calculateTotalPrice
        //  2. orderStatus 세팅하기 (결제수단(paymentType)에 따라서)

        order.setTotalPrice(50000);
        order.setOrderStatus(OrderStatus.ORDER_COMPLETED);
        order.setMemberId(UserContext.get().getMemberId());
        order.setCreatedAt(new Date());
        order.setCreatedBy(UserContext.get().getUserEmail());
        order.setModifiedAt(new Date());
        order.setModifiedBy(UserContext.get().getUserEmail());
        order.setDeleted(false);

        orderRepository.create(order);
        return order;
    }

    private void createOrderItems(Order createdOrder, List<OrderItem> items) {
        for (OrderItem item : items) {
            item.setOrderId(createdOrder.getId());
            item.setMemberId(UserContext.get().getMemberId());
            item.setOrderStatus(createdOrder.getOrderStatus());

            Book book = bookService.findOneById(item.getBookId());
            item.setSalePrice(book.getSalePrice());

            orderItemService.create(item);
        }
    }

    public Order findOneById(Long memberId) {
        return orderRepository.findOneById(memberId);
    }

    public List<Order> findAllByMemberId(Long memberId) {
        return orderRepository.findAllByMemberId(memberId);
    }

    public Long update(Order order) {
        order.setModifiedAt(new Date());
        order.setModifiedBy(UserContext.get().getUserEmail());

        return orderRepository.update(order);
    }

    /**
     * 주문 취소 시 order와 order_item 모두 삭제(deleted -> true 변경)
     * @param orderId
     */
    public void cancel(Long orderId) {
        Order order = findOneById(orderId);
        order.setModifiedAt(new Date());
        order.setModifiedBy(UserContext.get().getUserEmail());
        order.setDeleted(true);

        orderRepository.update(order);
        orderItemService.delete(orderId);
    }
}
