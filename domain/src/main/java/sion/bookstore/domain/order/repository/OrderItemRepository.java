package sion.bookstore.domain.order.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderItemRepository {
    void create(OrderItem product);
    Long update(OrderItem product);
    List<OrderItem> findAllByOrderId(Long orderId);
}
