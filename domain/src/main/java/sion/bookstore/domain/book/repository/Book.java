package sion.bookstore.domain.book.repository;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Book {
    private Long id;
    private String title;
    private String content;
    private String isbn10;
    private String isbn13;
    private List<Author> authors;
    private List<Translator> translators;
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