package sion.bookstore.domain.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@PropertySource("classpath:application-domain.properties")
@RequiredArgsConstructor
public class OnePageParser {
    @Value("${yes24.domain.url}")
    private String yes24Domain;

    private final OneBookParser oneBookParser;

    public List<ParsedBook> parse(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        List<ParsedBook> parsedBooks = new ArrayList<>();

        Elements elements = doc.select("ul.clearfix li div.cCont_goodsSet");
        log.info("=>elements size : {}", elements.size());

        for (Element element : elements) {
            ParsedBook parsedBook = new ParsedBook();
            Element linkEl = element.selectFirst("span.imgBdr a");
//            String imageUrl = linkEl.selectFirst("img").attr("src");

            if (Objects.isNull(linkEl)) {
                log.info("파싱되지 않고 패스 된 책 element : {}", element);
                continue;
            }

            String detailPath = linkEl.attr("href");
            parsedBook = oneBookParser.parse(yes24Domain + detailPath);

            if (Objects.nonNull(parsedBook)) {
                parsedBooks.add(parsedBook);
            }
            log.info("\t=>elements element : {}", element.tagName());
        }

        return parsedBooks;
    }
}
