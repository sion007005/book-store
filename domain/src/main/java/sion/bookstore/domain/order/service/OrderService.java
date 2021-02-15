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
import sion.bookstore.domain.payment.repository.PaymentType;
import sion.bookstore.domain.payment.service.PaymentService;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final PaymentService paymentService;
    private final CartService cartService;
    private final BookService bookService;

    public Long create(Order order, List<Long> cartItemIds) {
        Order createdOrder = createOrder(order);

        paymentService.executePaymentProcess(createdOrder); //결제 create
        changeOrderStatus(createdOrder); //orderStatus 업데이트

        createOrderItems(createdOrder, order.getItems());
        cartService.removeByItemIds(cartItemIds);

        return createdOrder.getId();
    }

    private void changeOrderStatus(Order order) {
        // TODO paymentType에 따라서 orderstatus값 세팅하고 업데이트
        // 현재는 무통장입금이면 입금대기, 그 외의 결제타입이면 주문완료로 세팅
        if (order.getPaymentType() == PaymentType.DEPOSIT) {
            order.setOrderStatus(OrderStatus.WAITING_DEPOSIT);
        } else {
            order.setOrderStatus(OrderStatus.ORDER_COMPLETED);
        }

        update(order);
    }

    private Order createOrder(Order order) {
        // TODO
        //  3. 재고수량 변경
        order.setTotalPrice(calculateTotalPrice(order.getItems()));
        order.setOrderStatus(OrderStatus.ORDER_CREATED);
        order.setMemberId(UserContext.get().getMemberId());
        order.setCreatedAt(new Date());
        order.setCreatedBy(UserContext.get().getUserEmail());
        order.setModifiedAt(new Date());
        order.setModifiedBy(UserContext.get().getUserEmail());
        order.setDeleted(false);

        orderRepository.create(order);
        return order;
    }

    private Integer calculateTotalPrice(List<OrderItem> items) {
        Integer totalPrice = 0;
        for (OrderItem item : items) {
            totalPrice += item.getSalePrice();
        }

        return totalPrice;
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
        // TODO 재고수량 변경 
        Order order = findOneById(orderId);
        order.setModifiedAt(new Date());
        order.setModifiedBy(UserContext.get().getUserEmail());
        order.setDeleted(true);

        orderRepository.update(order);
        orderItemService.delete(orderId);
    }
}
