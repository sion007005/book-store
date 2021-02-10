package sion.bookstore.front.controller.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import sion.bookstore.domain.auth.UserContext;
import sion.bookstore.domain.order.repository.Order;
import sion.bookstore.domain.order.service.OrderService;
import sion.bookstore.front.login.LoginRequired;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order/page")
    @LoginRequired
    public ModelAndView getOrderPage(Order order) {
        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("items", order.getItems());
        // TODO 최근 배송지
        mav.addObject("totalPrice", order.getTotalPrice());

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
