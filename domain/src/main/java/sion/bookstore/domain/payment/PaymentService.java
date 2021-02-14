package sion.bookstore.domain.payment;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PaymentService {

    public List<PaymentType> findPaymentTypes() {
        return Arrays.asList(PaymentType.CREDIT_CARD, PaymentType.DEPOSIT, PaymentType.NAVER_PAY);
    }
}
