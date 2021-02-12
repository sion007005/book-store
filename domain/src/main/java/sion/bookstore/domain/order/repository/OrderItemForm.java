package sion.bookstore.domain.order.repository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemForm {
    private Long cartItemId;
    private Integer count;
}
