package sion.bookstore.domain.cart.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CartRepository {
    Long add(CartItem cart);
    Long update(CartItem cart);
    CartItem findOneByBookId(Long bookId);
    List<CartItem> getCartItems(Long userId);
}
