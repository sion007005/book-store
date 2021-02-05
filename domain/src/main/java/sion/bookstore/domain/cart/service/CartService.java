package sion.bookstore.domain.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.cart.repository.CartItem;
import sion.bookstore.domain.cart.repository.CartRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public Long add(CartItem cart) {
        //TODO user 정보 넣기
        cart.setCreatedAt(new Date());
        cart.setCreatedBy("tester");
        cart.setModifiedAt(new Date());
        cart.setModifiedBy("tester");
        cart.setDeleted(false);

        cartRepository.add(cart);
        return cart.getId();
    }

    public Long update(CartItem cart) {
        cart.setModifiedAt(new Date());
        //TODO user 정보 넣기
        cart.setModifiedBy("tester");

        cartRepository.update(cart);
        return cart.getId();
    }

    public List<CartItem> getCartItems(Long userId) {
        List<CartItem> itemList = cartRepository.getCartItems(userId);
        return itemList;
    }
}
