package sion.bookstore.domain.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sion.bookstore.domain.BaseAuditor;
import sion.bookstore.domain.auth.UserContext;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.service.BookService;
import sion.bookstore.domain.cart.service.CartService;
import sion.bookstore.domain.email.MailService;
import sion.bookstore.domain.order.repository.Order;
import sion.bookstore.domain.order.repository.OrderItem;
import sion.bookstore.domain.order.repository.OrderRepository;
import sion.bookstore.domain.order.repository.OrderStatus;
import sion.bookstore.domain.payment.repository.PaymentType;
import sion.bookstore.domain.payment.service.PaymentService;
import sion.bookstore.domain.utils.MailUtil;

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
    private final MailUtil mailUtil;
    private final MailService mailService;

    public Long create(Order order, List<Long> cartItemIds) {
        Order createdOrder = createOrder(order);

        paymentService.executePaymentProcess(createdOrder); //결제 create
        changeOrderStatus(createdOrder); //orderStatus 업데이트
        createOrderItems(createdOrder, order.getItems());
        changeStockQuantity(order.getItems(), -1);
        cartService.removeByItemIds(cartItemIds);

        mailService.send(mailUtil.getOrderCompletionMail(order));
        return createdOrder.getId();
    }

    private void changeStockQuantity(List<OrderItem> items, Integer signNumber) {
        for (OrderItem item : items) {
            bookService.changeStockQuantity(item.getBookId(), item.getQuantity() * signNumber);
        }
    }

    private void changeOrderStatus(Order order) {
        // TODO paymentType에 따라서 orderstatus값 세팅하고 업데이트
        // 현재는 무통장입금이면 입금대기, 그 외의 결제타입이면 주문완료로 세팅
        if (order.getPaymentType() == PaymentType.REMITTANCE) {
            order.setOrderStatus(OrderStatus.WAITING_DEPOSIT);
        } else {
            order.setOrderStatus(OrderStatus.ORDER_COMPLETED);
        }

        update(order);
    }

    private Order createOrder(Order order) {
        order.setTotalPrice(calculateTotalPrice(order.getItems()));
        order.setOrderStatus(OrderStatus.ORDER_CREATED);
        order.setMemberId(UserContext.get().getMemberId());
        BaseAuditor.setCreationInfo(order);

        orderRepository.create(order);
        return order;
    }

    private Integer calculateTotalPrice(List<OrderItem> items) {
        Integer totalPrice = 0;
        for (OrderItem item : items) {
            totalPrice += (item.getSalePrice() * item.getQuantity());
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
        BaseAuditor.setUpdatingInfo(order);
        return orderRepository.update(order);
    }

    /**
     * 주문 취소 시 order와 order_item 모두 삭제(deleted -> true 변경)
     * @param orderId
     */
    public void cancel(Long orderId) {
        Order order = findOneById(orderId);
        BaseAuditor.setDeletionInfo(order);

        //본인의 주문만 취소할 수 있도록 해야 함
        if (order.getMemberId() != UserContext.get().getMemberId()) {
            throw new IllegalRequestException("잘못된 요청입니다.");
        }

        List<OrderItem> orderItemList = orderItemService.findAllByOrderId(orderId);
        changeStockQuantity(orderItemList, 1);
        orderRepository.update(order);
        orderItemService.delete(orderId);
        mailService.send(mailUtil.getOrderCancellationMail(order));
    }
}
