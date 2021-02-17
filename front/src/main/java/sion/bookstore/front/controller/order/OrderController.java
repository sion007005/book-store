package sion.bookstore.front.controller.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import sion.bookstore.domain.auth.UserContext;
import sion.bookstore.domain.member.repository.Address;
import sion.bookstore.domain.member.service.AddressService;
import sion.bookstore.domain.order.repository.Order;
import sion.bookstore.domain.order.repository.OrderItemForm;
import sion.bookstore.domain.order.service.OrderService;
import sion.bookstore.domain.payment.repository.PaymentType;
import sion.bookstore.domain.payment.service.PaymentService;
import sion.bookstore.front.login.LoginRequired;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final AddressService addressService;
    private final PaymentService paymentService;
    private final OrderConverter orderConverter;

    @PostMapping("/order/page")
    @LoginRequired
    public ModelAndView getOrderPage(CartForm cartForm) {
        List<CartForm.CartItemForm> cartItemForms = cartForm.getCartItemForms();

        if (CollectionUtils.isEmpty(cartItemForms)) {
            throw new IllegalOperationException("잘못된 요청입니다.");
        }

        List<OrderItemForm> orderItems = orderConverter.convertToOrderItemForm(cartItemForms);
        Address defaultAddress = addressService.findDefaultAddress(UserContext.get().getMemberId());
        List<PaymentType> paymentTypes = paymentService.findPaymentTypes();

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("orderItems", orderItems);
        mav.addObject("defaultAddress", defaultAddress);
        mav.addObject("paymentTypes", paymentTypes);
        return mav;
    }

    @PostMapping("/order/create")
    @LoginRequired
    public ModelAndView create(@ModelAttribute OrderForm orderForm) {
        Order order = orderConverter.convertToOrder(orderForm);
        Long createdOrderId = orderService.create(order, orderForm.getCartItemIds());

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
