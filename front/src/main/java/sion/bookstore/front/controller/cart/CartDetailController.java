package sion.bookstore.front.controller.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.domain.cart.repository.CartItem;
import sion.bookstore.domain.cart.service.CartService;
import sion.bookstore.front.ResponseData;

import java.util.List;

/**
 * 특정 회원의 장바구니에 담긴 상품 목록을 보여주는 컨트롤러
 */
@Controller
@RequiredArgsConstructor
public class CartDetailController {
    private final CartService cartService;

    @GetMapping("/cart/list")
    @ResponseBody
    public ResponseData getCartItems(Long userId) {
        List<CartItem> cartItems = cartService.getCartItems(userId);
        return ResponseData.success(cartItems);
    }
}
