package sion.bookstore.domain.book.service;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import sion.bookstore.domain.PageCondition;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class AuthorSearchCondition extends PageCondition {
    private String name;
}
