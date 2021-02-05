package sion.bookstore.front.controller.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import sion.bookstore.domain.cart.repository.CartItem;
import sion.bookstore.domain.cart.service.CartService;
import sion.bookstore.front.login.LoginRequired;

@Controller
@RequiredArgsConstructor
public class CartUpdateController {
    private final CartService cartService;

    @PostMapping("/cart/update")
    @LoginRequired
    public ModelAndView update(CartItem cartItem) {
        Long cartItemId = cartService.update(cartItem);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("cartItemId", cartItemId);

        return mav;
    }
}
