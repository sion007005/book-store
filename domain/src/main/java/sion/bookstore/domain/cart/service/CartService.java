package sion.bookstore.domain.cart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.BaseAuditor;
import sion.bookstore.domain.auth.UserContext;
import sion.bookstore.domain.cart.repository.CartItem;
import sion.bookstore.domain.cart.repository.CartRepository;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartValidator cartValidator;

    public Long add(CartItem cart) {
        //TODO CHECK validation
        cartValidator.validate(cart, "Cart");
        cart.setMemberId(UserContext.get().getMemberId());

        CartItem existingItem = findOneByBookId(cart.getBookId());
        if (Objects.nonNull(existingItem)) {
            existingItem.setQuantity(cart.getQuantity());
            return update(existingItem);
        }

        BaseAuditor.setCreationInfo(cart);

        cartRepository.add(cart);
        return cart.getId();
    }

    public CartItem findOneByCartId(Long id) {
        return cartRepository.findOneByCartId(id);
    }

    public CartItem findOneByBookId(Long bookId) {
        return cartRepository.findOneByBookId(bookId);
    }

    public List<CartItem> findAllByMemberId(Long memberId) {
        List<CartItem> itemList = cartRepository.findAllByMemberId(memberId);
        return itemList;
    }

    public void removeAllByMemberId(Long memberId) {
        List<CartItem> cartItems = cartRepository.findAllByMemberId(memberId);
        for (CartItem cartItem : cartItems) {
            BaseAuditor.setDeletionInfo(cartItem);
            cartRepository.update(cartItem);
        }
    }

    public void changeItemQuantity(CartItem item) {
        CartItem cartItem = findOneByCartId(item.getId());
        cartItem.setQuantity(item.getQuantity());
        update(cartItem);
    }

    private Long update(CartItem cart) {
        cart.setMemberId(UserContext.get().getMemberId());
        BaseAuditor.setUpdatingInfo(cart);

        cartRepository.update(cart);
        return cart.getId();
    }

    public void removeByItemIds(List<Long> cartItemIds) {
        for (Long cartItemId : cartItemIds) {
            deleteCartItem(cartItemId);
        }
    }

    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = findOneByCartId(cartItemId);
        if (Objects.isNull(cartItem)) {
            return;
        }

        BaseAuditor.setDeletionInfo(cartItem);
        cartRepository.update(cartItem);
    }
}
