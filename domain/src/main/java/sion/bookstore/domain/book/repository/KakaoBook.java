package sion.bookstore.domain.book.repository;

import lombok.Getter;
import lombok.Setter;
import sion.bookstore.domain.book.service.SaleStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class KakaoBook {
    private List<Document> documents = new ArrayList<>();

    @Getter
    @Setter
    public static class Document {
        private String title;
        private String contents;
        private String[] authors;
        private String[] translators;
        private Date datetime;
        private String publisher;
        private Integer price;
        private Integer sale_price;
        private String thumbnail;
        private SaleStatus status;
    }
}
