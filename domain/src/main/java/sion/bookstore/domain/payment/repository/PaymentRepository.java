package sion.bookstore.domain.payment.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PaymentRepository {
    Long create(Payment payment);
}
