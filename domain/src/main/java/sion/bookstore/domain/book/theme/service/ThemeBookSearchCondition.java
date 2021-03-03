package sion.bookstore.domain.book.theme.service;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import sion.bookstore.domain.PageCondition;

@Getter
@Setter
@EqualsAndHashCode
public class ThemeBookSearchCondition extends PageCondition {
    private Long themeSectionId;
}
