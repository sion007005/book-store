package sion.bookstore.domain.cart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.BaseAuditor;
import sion.bookstore.domain.auth.UserContext;
import sion.bookstore.domain.cart.repository.CartItem;
import sion.bookstore.domain.cart.repository.CartRepository;
import sion.bookstore.domain.utils.validator.ValidationException;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public Long add(CartItem cart) {
        //TODO validation check
        if (cart.getUserId() != UserContext.get().getMemberId()) {
            throw new ValidationException("잘못된 요청입니다.");
        }

        CartItem existingItem = findOneByCartId(cart.getId());
        if (Objects.nonNull(existingItem)) {
            existingItem.setDeleted(true);
            update(existingItem);
        }

        cart.setUserId(UserContext.get().getMemberId());
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

    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = findOneByCartId(cartItemId);
        if (Objects.isNull(cartItem)) {
            return;
        }

        BaseAuditor.setDeletionInfo(cartItem);
        cartRepository.update(cartItem);
    }

    private void update(CartItem cart) {
        if (cart.getUserId() != UserContext.get().getMemberId()) {
            throw new ValidationException("잘못된 요청입니다.");
        }

        cart.setUserId(UserContext.get().getMemberId());
        BaseAuditor.setUpdatingInfo(cart);

        cartRepository.update(cart);
    }

    public void removeByItemIds(List<Long> cartItemIds) {
        for (Long cartItemId : cartItemIds) {
            deleteCartItem(cartItemId);
        }
    }
}
