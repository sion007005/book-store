package sion.bookstore.domain.book.thema.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import sion.bookstore.domain.book.repository.Book;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ThemaSection {
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
