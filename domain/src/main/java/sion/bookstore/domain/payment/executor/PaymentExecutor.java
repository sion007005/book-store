package sion.bookstore.domain.payment.executor;

import sion.bookstore.domain.order.repository.Order;
import sion.bookstore.domain.payment.repository.Payment;

public interface PaymentExecutor {
    Payment executeAndMakePayment(Order order);
}
