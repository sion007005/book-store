package sion.bookstore.domain.order.service;

import lombok.Getter;
import lombok.Setter;
import sion.bookstore.domain.PageCondition;

@Getter
@Setter
public class OrderSearchCondition extends PageCondition {
    Long memberId;
}
