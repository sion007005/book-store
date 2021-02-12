package sion.bookstore.front.controller.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import sion.bookstore.domain.auth.UserContext;
import sion.bookstore.domain.cart.repository.CartItem;
import sion.bookstore.domain.cart.service.CartService;
import sion.bookstore.domain.member.repository.Address;
import sion.bookstore.domain.member.service.AddressService;
import sion.bookstore.domain.order.repository.Order;
import sion.bookstore.domain.order.repository.OrderItemForm;
import sion.bookstore.domain.order.service.OrderService;
import sion.bookstore.front.login.LoginRequired;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final CartService cartService;
    private final AddressService addressService;

    @PostMapping("/order/page")
    @LoginRequired
    public ModelAndView getOrderPage(List<OrderItemForm> items) {
        if (items.isEmpty()) {
            throw new IllegalOperationException("잘못된 요청입니다.");
        }

        List<CartItem> cartItemList = cartService.changeQuantityByItemList(items);
        Address defaultAddress = addressService.findDefaultAddress(UserContext.get().getMemberId());

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("items", cartItemList);
        mav.addObject("defaultAddress", defaultAddress);
        // TODO 결제 수단 목록
        return mav;
    }

    @PostMapping("/order/create")
    @LoginRequired
    public ModelAndView order(Order order) {
        Long createdOrderId = orderService.create(order);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("orderId", createdOrderId);
        return mav;
    }

    @GetMapping("/order/list")
    @LoginRequired
    public ModelAndView getMyOrderList() {
        List<Order> orderList = orderService.findAllByMemberId(UserContext.get().getMemberId());

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("orderList", orderList);

        return mav;
    }

    @GetMapping("/order/cancel/{orderId}")
    @LoginRequired
    public ModelAndView cancel(@PathVariable Long orderId) {
        orderService.cancel(orderId);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("orderId", orderId);
        return mav;
    }
}
