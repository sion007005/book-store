package sion.bookstore.domain.order.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import sion.bookstore.domain.cart.repository.CartItem;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Order {
    private Long id;
    private Long userId;
    private List<CartItem> items;
    private Integer totalPrice;
    private String paymentType;
    private String orderStatus;
    private String addressBasic;
    private String addressDetail;
    private Integer zipCode;
    private String phone;
    private String message;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    private String createdBy;
    private Date modifiedAt;
    private String modifiedBy;
    private boolean deleted;
}
