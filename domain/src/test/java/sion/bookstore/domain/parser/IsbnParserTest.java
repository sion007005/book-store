package sion.bookstore.domain.parser;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Slf4j
public class IsbnParserTest {

    @Test
    public void parse() throws IOException {
        String url = "http://www.yes24.com/24/Category/Display/001001001006";
        IsbnParser isbnParser = new IsbnParser();
        List<String> isbnList = isbnParser.parse(url);

        assertEquals(20, isbnList.size());

    }

}
