package sion.bookstore.domain.book.repository;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Book {
    private Long id;
    private String title;
    private String content;
    private String isbn;
    private String isbn13;
    private Date publishedAt;
    private String publisher;
    private Integer price;
    private Integer sale_price;
    private String thumbnail;
    private String status;
    private Date createdAt;
    private String createdBy;
    private Date modifiedAt;
    private String modifiedBy;
    private boolean deleted;
}
