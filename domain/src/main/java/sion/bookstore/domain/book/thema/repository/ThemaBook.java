package sion.bookstore.domain.book.thema.repository;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ThemaBook {
    private Long id;
    private Long themaSectionId;
    private Long bookId;
    private Date createdAt;
    private String createdBy;
    private Date modifiedAt;
    private String modifiedBy;
    private boolean deleted;
}
