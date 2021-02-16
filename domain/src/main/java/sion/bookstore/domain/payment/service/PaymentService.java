package sion.bookstore.domain.payment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.BaseAuditor;
import sion.bookstore.domain.order.repository.Order;
import sion.bookstore.domain.payment.executor.PaymentExecutor;
import sion.bookstore.domain.payment.executor.PaymentExecutorFactory;
import sion.bookstore.domain.payment.repository.Payment;
import sion.bookstore.domain.payment.repository.PaymentRepository;
import sion.bookstore.domain.payment.repository.PaymentType;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private  final PaymentRepository paymentRepository;

    public Long create(Payment payment) {
        return paymentRepository.create(payment);
    }

    public List<PaymentType> findPaymentTypes() {
        return Arrays.asList(PaymentType.CREDIT_CARD, PaymentType.REMITTANCE, PaymentType.NAVER_PAY, PaymentType.KAKAO_PAY);
    }

    public void executePaymentProcess(Order order) {
        PaymentExecutor executor = PaymentExecutorFactory.getInstance(order.getPaymentType());
        Payment payment = executor.executeAndMakePayment(order);

        payment.setMemberId(order.getMemberId());
        payment.setOrderId(order.getId());
        payment.setPaymentType(order.getPaymentType());
        BaseAuditor.setCreationInfo(payment);

        create(payment);
    }
}
