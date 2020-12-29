package sion.bookstore.domain.parser;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;

@Slf4j
public class CategoryParserTest {

    @Test
    public void parse() throws IOException {
        String url = "http://www.yes24.com/Mall/Main/Book/001?CategoryNumber=001";
        CategoryParser parser = new CategoryParser();
        parser.parse(url);
    }

}
