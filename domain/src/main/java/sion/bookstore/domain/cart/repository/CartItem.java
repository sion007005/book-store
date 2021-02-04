package sion.bookstore.domain.cart.repository;

import lombok.Getter;
import lombok.Setter;
import sion.bookstore.domain.book.repository.Book;

import java.util.Date;

@Getter
@Setter
public class CartItem {
    private Long id;
    private Long userId;
    private Long bookId;
    private Integer quantity;
    private Date createdAt;
    private String createdBy;
    private Date modifiedAt;
    private String modifiedBy;
    private boolean deleted;
    private Book book;
}
