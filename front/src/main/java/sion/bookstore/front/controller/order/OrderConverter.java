package sion.bookstore.front.controller.order;

import sion.bookstore.domain.order.repository.Order;
import sion.bookstore.domain.order.repository.OrderItem;
import sion.bookstore.domain.order.repository.OrderItemForm;

import java.util.ArrayList;
import java.util.List;

public class OrderConverter {
    public static List<OrderItem> convertToOrderItem(List<CartForm.CartItemForm> cartItemForms) {
        List<OrderItem> orderItemList = new ArrayList<>();
        for (CartForm.CartItemForm cartItemForm : cartItemForms) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBookId(cartItemForm.getBookId());
            orderItem.setQuantity(cartItemForm.getQuantity());
            orderItemList.add(orderItem);
        }
        return orderItemList;
    }

    public static Order convertToOrder(OrderForm orderForm) {
        Order order = new Order();

        List<OrderItem> orderItems = new ArrayList<>();
        List<OrderItemForm> orderItemForms = orderForm.getOrderItemForms();
        for (OrderItemForm orderItemForm : orderItemForms) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBookId(orderItemForm.getBookId());
            orderItem.setQuantity(orderItemForm.getQuantity());
            orderItem.setSalePrice(orderItemForm.getSalePrice());

            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        order.setPaymentType(orderForm.getPaymentType());
        order.setAddressBasic(orderForm.getAddressBasic());
        order.setAddressDetail(orderForm.getAddressDetail());
        order.setZipCode(orderForm.getZipCode());
        order.setPhone(orderForm.getPhone());
        order.setMessage(orderForm.getMessage());

        return order;
    }
}
