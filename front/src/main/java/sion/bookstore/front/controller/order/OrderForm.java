package sion.bookstore.front.controller.order;

import lombok.Getter;
import lombok.Setter;
import sion.bookstore.domain.order.repository.OrderItemForm;
import sion.bookstore.domain.payment.repository.PaymentType;

import java.util.List;

@Getter
@Setter
public class OrderForm {
    private PaymentType paymentType;
    private String addressBasic;
    private String addressDetail;
    private Integer zipCode;
    private String phone;
    private String message;
    private List<OrderItemForm> orderItemForms;
    private List<Long> cartItemIds;
}
