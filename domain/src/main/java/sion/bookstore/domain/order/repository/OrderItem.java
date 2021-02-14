package sion.bookstore.domain.order.repository;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderItem {
    private Long id;
    private Long orderId;
    private Long memberId;
    private Long bookId;
    private Integer quantity;
    private Integer salePrice;
    private OrderStatus orderStatus;

    private Date createdAt;
    private String createdBy;
    private Date modifiedAt;
    private String modifiedBy;
    private boolean deleted;
}
