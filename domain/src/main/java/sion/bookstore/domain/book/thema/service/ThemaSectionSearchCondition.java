package sion.bookstore.domain.book.thema.service;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import sion.bookstore.domain.PageCondition;

@Getter
@Setter
@EqualsAndHashCode
public class ThemaSectionSearchCondition extends PageCondition {
    private String keyword;
}
