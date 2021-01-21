package sion.bookstore.domain.book.service;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import sion.bookstore.domain.PageCondition;

@Getter
@Setter
@EqualsAndHashCode
public class BookSearchCondition extends PageCondition {
    private String title;
    private Long categoryId;
    private String name;
    private String isbn; // isbn10, isbn13 둘 다 지원
    private String orderType;
}
