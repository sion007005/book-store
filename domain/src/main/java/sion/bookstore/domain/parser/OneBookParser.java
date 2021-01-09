package sion.bookstore.domain.parser;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.book.service.BookService;
import sion.bookstore.domain.book.service.KaKaoBookService;
import sion.bookstore.domain.category.repository.CategoryRepository;

import java.io.IOException;

@Slf4j
@Service
public class OneBookParser {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OnePageParser onePageParser;

    @Autowired
    private KaKaoBookService kaKaoBookService;

    @Autowired
    private BookService bookService;

    public ParsedBook parse(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        ParsedBook parsedBook = new ParsedBook();

        if (doc.location().contains("LOGIN")) {
            // 성인인증이 필요한 상품이면 건너뛴다. (임시)
            log.info("성인인증 필요한 책 패스 : {}", url);
            return null;
        }

        Element linkEl = doc.selectFirst("div.gd_imgArea span.gd_img img");
        String yes24ImageUrl = linkEl.attr("src");

        parsedBook.setImageUrl(yes24ImageUrl);

        Elements trEls = doc.selectFirst("div#infoset_specific").select("table.tb_nor tbody.b_size tr");

        for (Element trEl : trEls) {
            if ("ISBN13".equals(trEl.selectFirst("th").text())) {
                parsedBook.setIsbn13(trEl.selectFirst("td").text());
            } else if ("ISBN10".equals(trEl.selectFirst("th").text())) {
                parsedBook.setIsbn10(trEl.selectFirst("td").text());
            }
        }

        return parsedBook;
    }
}
