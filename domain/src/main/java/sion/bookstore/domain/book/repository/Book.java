package sion.bookstore.domain.book.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import sion.bookstore.domain.BaseAudit;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
public class Book extends BaseAudit {
    private Long id;
    private String title;
    private String content;
    private String isbn10;
    private String isbn13;
    private Integer stockQuantity;

    private String author1;
    private String author2;
    private String author3;
    private String translator1;
    private String translator2;
    private String translator3;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publishedAt;
    private String publisher;
    private Integer price;
    private Integer salePrice;
    private String thumbnail;
    private String status;
    private MultipartFile coverImageFile;
}
