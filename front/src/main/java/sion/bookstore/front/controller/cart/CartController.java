package sion.bookstore.front.controller.cart;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
        List<CartItem> cartItems = cartService.getCartItems(user.getMemberId());

        return ResponseData.success(cartItems);
    }

    @PostMapping("/cart/update")
    @LoginRequired
    public ModelAndView update(CartItem cartItem) {
        Long cartItemId = cartService.update(cartItem);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("cartItemId", cartItemId);

        return mav;
    }
}
