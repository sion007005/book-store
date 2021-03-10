package sion.bookstore.domain.payment.executor;

import sion.bookstore.domain.order.repository.Order;
import sion.bookstore.domain.payment.repository.Payment;

public class RemittancePaymentExecutor implements PaymentExecutor {
    @Override
    public Payment executeAndMakePayment(Order order) {
        // 가상계좌를 받아와서 결제 테이블에 세팅
        String virtualAccount = getVirtualAccount();

        Payment payment = new Payment();
        payment.setVirtualAccount(virtualAccount);

        return payment;
    }

    private String getVirtualAccount() {
        //예외가 생기면 orderStatus에 세팅
        return "12345";
    }
}
