package sion.bookstore.domain.member.service;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import sion.bookstore.domain.PageCondition;

@Getter
@Setter
@EqualsAndHashCode
public class MemberSearchCondition extends PageCondition {
    private String keyword;
}
