package sion.bookstore.domain.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sion.bookstore.domain.auth.UserContext;
import sion.bookstore.domain.order.repository.OrderProduct;
import sion.bookstore.domain.order.repository.OrderProductRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderProductService {
    private final OrderProductRepository orderProductRepository;

    public void create(OrderProduct orderProduct) {
        orderProduct.setCreatedAt(new Date());
        orderProduct.setCreatedBy(UserContext.get().getUserEmail());
        orderProduct.setModifiedAt(new Date());
        orderProduct.setModifiedBy(UserContext.get().getUserEmail());
        orderProduct.setDeleted(false);

        orderProductRepository.create(orderProduct);
    }

    public Long update(OrderProduct orderProduct) {
        orderProduct.setModifiedAt(new Date());
        orderProduct.setModifiedBy(UserContext.get().getUserEmail());

        return orderProductRepository.update(orderProduct);
    }

    public void delete(Long orderId) {
        List<OrderProduct> productList = orderProductRepository.findAllByOrderId(orderId);
        for (OrderProduct product : productList) {
            product.setDeleted(true);
            update(product);
        }
    }
}

