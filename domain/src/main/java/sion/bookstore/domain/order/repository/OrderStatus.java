package sion.bookstore.domain.order.repository;

public enum OrderStatus {
    // 입금대기/승인대기/승인실패
    // 주문완료/주문취소
    ORDER_CREATED,
    WAITING_DEPOSIT, /* 입금대기 */
    DEPOSIT_COMPLETED, /* 입금완료 */
    CREDIT_CARD_PAYMENT_COMPLETED, /* 카드 결제 완료 */
    CREDIT_CARD_PAYMENT_FAILED, /* 카드 승인(결제) 실패 */
    ORDER_COMPLETED, /* 주문완료 */
    ORDER_CANCELED /* 주문취소 */
}
