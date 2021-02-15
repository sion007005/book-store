package sion.bookstore.domain.book.theme.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import sion.bookstore.domain.BaseAudit;
import sion.bookstore.domain.book.repository.Book;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ThemeSection extends BaseAudit {
    private Long id;
    private Integer orderNo;
    private String type;
    private String title;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    private String createdBy;
    private Date modifiedAt;
    private String modifiedBy;
    private boolean deleted;
    private List<Book> books;
}
