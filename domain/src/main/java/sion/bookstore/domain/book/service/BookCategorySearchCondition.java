package sion.bookstore.domain.book.service;

import sion.bookstore.domain.PageCondition;

public class BookCategorySearchCondition extends PageCondition {
    private String keyword;
    private Long categoryId;
}
