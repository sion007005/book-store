package sion.bookstore.domain.cart.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import sion.bookstore.domain.book.repository.Book;

import java.util.Date;

@Getter
@Setter
public class CartItem {
    private Long id;
    private Long userId;
    private Long bookId;
    private Integer quantity;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    private String createdBy;
    private Date modifiedAt;
    private String modifiedBy;
    private boolean deleted;
    private Book book;
}
