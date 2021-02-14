package sion.bookstore.domain.payment;

public enum PaymentType {
    CREDIT_CARD("신용카드"),
    DEPOSIT("무통장입금"),
    NAVER_PAY("네이버페이"),
    KAKAO_PAY("카카오페이");

    String desc;

    PaymentType(String desc) {
        this.desc = desc;
    }
}
