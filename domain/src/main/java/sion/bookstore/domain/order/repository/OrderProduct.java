package sion.bookstore.domain.order.repository;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderProduct {
    private Long id;
    private Long orderId;
    private Long bookId;
    private Integer quantity;

    private Date createdAt;
    private String createdBy;
    private Date modifiedAt;
    private String modifiedBy;
    private boolean deleted;
}
