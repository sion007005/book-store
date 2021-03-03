package sion.bookstore.domain.book.theme.repository;

import lombok.Getter;
import lombok.Setter;
import sion.bookstore.domain.BaseAudit;

import java.util.Date;

@Getter
@Setter
public class ThemeBook extends BaseAudit {
    private Long id;
    private Long themeSectionId;
    private Long bookId;
    private Date createdAt;
    private String createdBy;
    private Date modifiedAt;
    private String modifiedBy;
    private boolean deleted;
}
