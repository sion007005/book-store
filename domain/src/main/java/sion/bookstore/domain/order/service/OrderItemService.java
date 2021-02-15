package sion.bookstore.domain.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sion.bookstore.domain.BaseAuditor;
import sion.bookstore.domain.order.repository.OrderItem;
import sion.bookstore.domain.order.repository.OrderItemRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public void create(OrderItem orderItem) {
        BaseAuditor.setCreationInfo(orderItem);
        orderItemRepository.create(orderItem);
    }

    public Long update(OrderItem orderItem) {
        BaseAuditor.setUpdatingInfo(orderItem);
        return orderItemRepository.update(orderItem);
    }

    public void delete(Long orderId) {
        List<OrderItem> productList = orderItemRepository.findAllByOrderId(orderId);
        for (OrderItem product : productList) {
            BaseAuditor.setDeletionInfo(product);
            orderItemRepository.update(product);
        }
    }
}

