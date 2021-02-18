package sion.bookstore.domain.book.service;

import lombok.Getter;

@Getter
public enum SaleStatus {
    IN_STOCK("판매가능"),
    BACK_ORDERED("일시품절"),
    OUT_OF_STOCK("판매불가");

    String status;

    SaleStatus(String status) {
        this.status = status;
    }
}
