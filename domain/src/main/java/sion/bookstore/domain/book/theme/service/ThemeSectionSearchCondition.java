package sion.bookstore.domain.book.theme.service;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import sion.bookstore.domain.PageCondition;

@Getter
@Setter
@EqualsAndHashCode
public class ThemeSectionSearchCondition extends PageCondition {
    private String keyword;
}
