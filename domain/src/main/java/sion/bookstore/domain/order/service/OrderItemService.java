package sion.bookstore.domain.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sion.bookstore.domain.auth.UserContext;
import sion.bookstore.domain.order.repository.OrderItem;
import sion.bookstore.domain.order.repository.OrderItemRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public void create(OrderItem orderItem) {
        orderItem.setCreatedAt(new Date());
        orderItem.setCreatedBy(UserContext.get().getUserEmail());
        orderItem.setModifiedAt(new Date());
        orderItem.setModifiedBy(UserContext.get().getUserEmail());
        orderItem.setDeleted(false);

        orderItemRepository.create(orderItem);
    }

    public Long update(OrderItem orderItem) {
        orderItem.setModifiedAt(new Date());
        orderItem.setModifiedBy(UserContext.get().getUserEmail());

        return orderItemRepository.update(orderItem);
    }

    public void delete(Long orderId) {
        List<OrderItem> productList = orderItemRepository.findAllByOrderId(orderId);
        for (OrderItem product : productList) {
            product.setDeleted(true);
            update(product);
        }
    }
}

