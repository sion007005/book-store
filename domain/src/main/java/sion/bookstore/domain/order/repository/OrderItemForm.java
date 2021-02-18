package sion.bookstore.domain.order.repository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemForm {
    private Long bookId;
    private String title;
    private String coverImagePath;
    private Integer quantity;
    private Integer salePrice;
}
