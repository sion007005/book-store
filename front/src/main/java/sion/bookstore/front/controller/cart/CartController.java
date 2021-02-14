package sion.bookstore.front.controller.cart;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sion.bookstore.domain.auth.User;
import sion.bookstore.domain.auth.UserContext;
import sion.bookstore.domain.cart.repository.CartItem;
import sion.bookstore.domain.cart.service.CartService;
import sion.bookstore.front.ResponseData;
import sion.bookstore.front.login.LoginRequired;

import java.util.List;
@Slf4j
@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/cart/add")
    @LoginRequired
    public ModelAndView add(CartItem cartItem) {
        Long cartItemId = cartService.add(cartItem);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("cartItemId", cartItemId);

        return mav;
    }

    @GetMapping("/my-cart")
    @ResponseBody
    @LoginRequired
    public ResponseData getCartItems() {
        User user = UserContext.get();
        List<CartItem> cartItems = cartService.findAllByMemberId(user.getMemberId());

        return ResponseData.success(cartItems);
    }

    /**
     * 장바구니 페이지에서 ajax 요청으로 수량 변경 시 사용는 메소드
     */
    @PostMapping("/item/change")
    @LoginRequired
    public ModelAndView changeQuantity(CartItem cartItem) {
        cartService.changeItemQuantity(cartItem);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("cartItemId", cartItem.getId());

        return mav;
    }

    /**
     * 장바구니 페이지에서 ajax 요청으로 상품 삭제 시 사용하는 메소드
     */
    @PostMapping("/item/delete/{cartItemId}")
    @LoginRequired
    public ModelAndView deleteCartItem(@PathVariable Long cartItemId) {
        cartService.deleteCartItem(cartItemId);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("cartItemId", cartItemId);

        return mav;
    }

}
