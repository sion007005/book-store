package sion.bookstore.domain.parser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParsedBook {
    private String isbn10;
    private String isbn13;
    private String imageUrl;
}
