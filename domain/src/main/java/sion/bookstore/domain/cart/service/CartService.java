package sion.bookstore.domain.cart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.auth.UserContext;
import sion.bookstore.domain.cart.repository.CartItem;
import sion.bookstore.domain.cart.repository.CartRepository;
import sion.bookstore.domain.order.repository.OrderItemForm;
import sion.bookstore.domain.utils.validator.ValidationException;

import java.util.Date;
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

        CartItem existingItem = findOneByBookId(cart.getBookId());
        if (Objects.nonNull(existingItem)) {
            existingItem.setDeleted(true);
            update(existingItem);
        }

        cart.setUserId(UserContext.get().getMemberId());
        cart.setCreatedAt(new Date());
        cart.setCreatedBy(UserContext.get().getUserEmail());
        cart.setModifiedAt(new Date());
        cart.setModifiedBy(UserContext.get().getUserEmail());
        cart.setDeleted(false);

        cartRepository.add(cart);
        return cart.getId();
    }

    public CartItem findOneByBookId(Long bookId) {
        return cartRepository.findOneByBookId(bookId);
    }

    public Long update(CartItem cart) {
        if (cart.getUserId() != UserContext.get().getMemberId()) {
            throw new ValidationException("잘못된 요청입니다.");
        }

        cart.setUserId(UserContext.get().getMemberId());
        cart.setModifiedAt(new Date());
        cart.setModifiedBy(UserContext.get().getUserEmail());

        cartRepository.update(cart);
        return cart.getId();
    }

    public List<CartItem> getCartItems(Long userId) {
        List<CartItem> itemList = cartRepository.getCartItems(userId);
        return itemList;
    }

    public void removeAllItems(Long memberId) {
        List<CartItem> cartItems = cartRepository.getCartItems(memberId);
        for (CartItem cartItem : cartItems) {
            cartItem.setDeleted(true);
            update(cartItem);
        }
    }

    public List<CartItem> changeItemQuantity(List<OrderItemForm> items) {
        // TODO
        // 1. 디비 cart 수량 업데이트
        // 2. 카트 아이템 리스트 반환
        return null;
    }
}
