package sion.bookstore.domain.payment.executor;

import sion.bookstore.domain.order.repository.Order;
import sion.bookstore.domain.payment.repository.Payment;

public class KakaoPaymentExecutor implements PaymentExecutor {
    @Override
    public Payment executeAndMakePayment(Order order) {
        String approvalNumber = getApprovalNumber();

        Payment payment = new Payment();
        payment.setApprovalNumber(approvalNumber);

        return payment;
    }

    private String getApprovalNumber() {
        return "12345";
    }
}
