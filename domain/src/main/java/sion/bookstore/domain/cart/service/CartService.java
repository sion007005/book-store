package sion.bookstore.domain.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.cart.repository.CartItem;
import sion.bookstore.domain.cart.repository.CartRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public Long add(CartItem cart) {
        cartRepository.add(cart);
        return cart.getId();
    }

    public Long update(CartItem cart) {
        cartRepository.update(cart);
        return cart.getId();
    }

    public List<CartItem> getCartItems(Long userId) {
        List<CartItem> itemList = cartRepository.getCartItems(userId);
        return itemList;
    }
}
