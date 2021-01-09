package sion.bookstore.domain.parser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParsedCategory {
    private int level;
    private String name;
    private String link;
    private String number;
    private String parentNumber;

    public ParsedCategory(int level, String name, String link, String number, String parentNumber) {
        this.level = level;
        this.name = name;
        this.link = link;
        this.number = number;
        this.parentNumber = parentNumber;
    }
}
