package sion.bookstore.domain.book;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;

@Slf4j
public class HtmlParserTest {

    @Test
    public void test() throws IOException {
        Document doc = Jsoup.connect("http://example.com/").get();
        String title = doc.title();
        log.info("title: {}" ,title);
    }
}
