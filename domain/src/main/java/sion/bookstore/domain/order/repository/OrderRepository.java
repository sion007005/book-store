package sion.bookstore.domain.order.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderRepository {
    Long create(Order order);
    Order findOneById(Long memberId);
    List<Order> findAllByMemberId(Long memberId);
    Long update(Order order);
}
