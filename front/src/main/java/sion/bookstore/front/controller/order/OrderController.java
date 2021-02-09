package sion.bookstore.front.controller.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import sion.bookstore.domain.order.repository.Order;
import sion.bookstore.domain.order.service.OrderService;
import sion.bookstore.front.login.LoginRequired;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping("/order/page")
    @LoginRequired
    public ModelAndView getOrderPage(Order order) {
        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("items", order.getItems());
        // TODO 최근 배송지
        mav.addObject("totalPrice", order.getTotalPrice());

        return mav;
    }

    @PostMapping("/order")
    @LoginRequired
    public ModelAndView order(Order order) {
        Long createdOrderId = orderService.create(order);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("orderId", createdOrderId);
        return mav;
    }
}
