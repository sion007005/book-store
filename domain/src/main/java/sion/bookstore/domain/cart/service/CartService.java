package sion.bookstore.domain.cart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.auth.UserContext;
import sion.bookstore.domain.cart.repository.CartItem;
import sion.bookstore.domain.cart.repository.CartRepository;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public Long add(CartItem cart) {
        log.info("user : {}", UserContext.get());
        cart.setUserId(UserContext.get().getMemberId());
        cart.setCreatedAt(new Date());
        cart.setCreatedBy(UserContext.get().getUserEmail());
        cart.setModifiedAt(new Date());
        cart.setModifiedBy(UserContext.get().getUserEmail());
        cart.setDeleted(false);

        cartRepository.add(cart);
        return cart.getId();
    }

    public Long update(CartItem cart) {
        cart.setModifiedAt(new Date());
        cart.setModifiedBy(UserContext.get().getUserEmail());

        cartRepository.update(cart);
        return cart.getId();
    }

    public List<CartItem> getCartItems(Long userId) {
        List<CartItem> itemList = cartRepository.getCartItems(userId);
        return itemList;
    }
}
