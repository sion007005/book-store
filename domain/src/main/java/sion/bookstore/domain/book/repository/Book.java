package sion.bookstore.domain.book.repository;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
public class Book {
    private Long id;
    private String title;
    private String content;
    private String isbn;
    private String isbn13;
    private Date publishedAt;
    private String publisher;
    private Integer price;
    private Integer salePrice;
    private String thumbnail;
    private String status;
    private Date createdAt;
    private String createdBy;
    private Date modifiedAt;
    private String modifiedBy;
    private boolean deleted;
}
