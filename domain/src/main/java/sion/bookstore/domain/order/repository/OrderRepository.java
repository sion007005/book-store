package sion.bookstore.domain.order.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderRepository {
    Long create(Order order);
}
