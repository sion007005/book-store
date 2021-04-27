package sion.bookstore.front.controller.order;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import sion.bookstore.domain.auth.UserContext;
import sion.bookstore.domain.cart.repository.CartItem;
import sion.bookstore.domain.cart.service.CartService;
import sion.bookstore.domain.member.repository.Address;
import sion.bookstore.domain.member.service.AddressService;
import sion.bookstore.domain.order.repository.Order;
import sion.bookstore.domain.order.repository.OrderItem;
import sion.bookstore.domain.order.repository.OrderItemForm;
import sion.bookstore.domain.order.service.OrderItemService;
import sion.bookstore.domain.order.service.OrderSearchCondition;
import sion.bookstore.domain.order.service.OrderService;
import sion.bookstore.domain.payment.repository.PaymentType;
import sion.bookstore.domain.payment.service.PaymentService;
import sion.bookstore.front.login.LoginRequired;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final AddressService addressService;
    private final PaymentService paymentService;
    private final OrderConverter orderConverter;
    private final CartService cartService;

    @PostMapping("/order/page")
    @LoginRequired
    public ModelAndView getOrderPage(CartForm cartForm) {
        List<CartForm.CartItemForm> cartItemForms = cartForm.getCartItemForms();

        if (CollectionUtils.isEmpty(cartItemForms)) {
            throw new IllegalOperationException("주문 할 상품이 없습니다.");
        }

        checkOrderTypeAndAddCart(cartItemForms);
        List<OrderItemForm> orderItems = orderConverter.convertToOrderItemForm(cartItemForms);
        Address defaultAddress = addressService.findDefaultAddress(UserContext.get().getMemberId());
        List<PaymentType> paymentTypes = paymentService.findPaymentTypes();

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("orderItems", orderItems);
        mav.addObject("defaultAddress", defaultAddress);
        mav.addObject("paymentTypes", paymentTypes);
        return mav;
    }

    /**
     * 장바구니가 아닌 상품 상세페이지에서 바로 주문하기를 선택할 경우,
     * 해당 상품을 장바구니에 추가 후 주문을 생성한다.
     * @param cartItemForms
     */
    private void checkOrderTypeAndAddCart(List<CartForm.CartItemForm> cartItemForms) {
        CartForm.CartItemForm cartItemForm = cartItemForms.get(0);

        if (Objects.nonNull(cartItemForm.getCartItemId())) {
            return;
        }

        CartItem cartItem = new CartItem();
        cartItem.setBookId(cartItemForm.getBookId());
        cartItem.setQuantity(cartItemForm.getQuantity());
        cartService.add(cartItem);

        cartItemForm.setCartItemId(cartItem.getId());
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
        //  결제정보, 주문한 책 정보들(가격은 주문할 당시의 가격이어야 함)
        OrderSearchCondition condition = new OrderSearchCondition();
        condition.setMemberId(UserContext.get().getMemberId());

        Page<Order> orderList = orderService.findAllByMemberId(condition);
        for (Order order : orderList) {
            List<OrderItem> items = orderItemService.findAllByOrderId(order.getId());
            order.setItems(items);
        }

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
