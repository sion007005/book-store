package sion.bookstore.domain.category.service;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import sion.bookstore.domain.PageCondition;

@Getter
@Setter
@EqualsAndHashCode
public class CategorySearchCondition extends PageCondition {
    private String keyword;
}
