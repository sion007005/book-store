package sion.bookstore.domain.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sion.bookstore.domain.auth.UserContext;
import sion.bookstore.domain.cart.repository.CartItem;
import sion.bookstore.domain.order.repository.Order;
import sion.bookstore.domain.order.repository.OrderProduct;
import sion.bookstore.domain.order.repository.OrderRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductService orderProductService;

    public Long create(Order order) {
        // TODO CHECK 'orderStatus 값' : 프론트 단에서, (카드 등) 결제가 완료 되었으면 '결제완료'로 보내고, 아니면 '결제대기'로 보내야 함..!
        order.setCreatedAt(new Date());
        order.setCreatedBy(UserContext.get().getUserEmail());
        order.setModifiedAt(new Date());
        order.setModifiedBy(UserContext.get().getUserEmail());
        order.setDeleted(false);

        Long createdOrderId = orderRepository.create(order);
        List<CartItem> items = order.getItems();
        mappingOrderProduct(createdOrderId, items);

        return createdOrderId;
    }

    private void mappingOrderProduct(Long orderId, List<CartItem> items) {
        for (CartItem item : items) {
            OrderProduct product = new OrderProduct();
            product.setOrderId(orderId);
            product.setBookId(item.getBookId());
            product.setQuantity(item.getQuantity());
            //TODO CHECK 바로 repository를 호출하는게 나은가?
            orderProductService.create(product);
        }
    }
}
