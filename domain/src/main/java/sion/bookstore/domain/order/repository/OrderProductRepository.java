package sion.bookstore.domain.order.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderProductRepository {
    void create(OrderProduct product);
    Long update(OrderProduct product);
    List<OrderProduct> findAllByOrderId(Long orderId);
}
