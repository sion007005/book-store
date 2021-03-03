package sion.bookstore.domain.book.theme.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import sion.bookstore.domain.BaseAudit;
import sion.bookstore.domain.book.repository.Book;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ThemeSection extends BaseAudit {
    private Long id;
    private Integer orderNo;
    private String type;
    private String title;
    private String description;
    private List<Book> books;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime displayStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime displayEndDate;
    private boolean onDisplay;
}
