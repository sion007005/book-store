package sion.bookstore.domain.payment.executor;

import sion.bookstore.domain.payment.repository.PaymentType;

import java.util.HashMap;
import java.util.Map;

public class PaymentExecutorFactory {
    private final static Map<PaymentType, PaymentExecutor> executors = new HashMap<>();
    static {
        executors.put(PaymentType.DEPOSIT, new DepositPaymentExecutor());
        executors.put(PaymentType.CREDIT_CARD, new CreditPaymentExecutor());
        executors.put(PaymentType.NAVER_PAY, new NaverPaymentExecutor());
        executors.put(PaymentType.KAKAO_PAY, new KakaoPaymentExecutor());
    }

    public static PaymentExecutor getInstance(PaymentType paymentType) {
        PaymentExecutor executor = executors.get(paymentType);
        return executor;
    }
}
