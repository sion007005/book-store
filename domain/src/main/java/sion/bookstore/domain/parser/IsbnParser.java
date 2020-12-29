package sion.bookstore.domain.parser;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class IsbnParser {
    private final String yes24Domain = "http://www.yes24.com/";

    public List<String> parse(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();

        Elements elements = doc.select("ul.clearfix li");

        log.info("elements size : {}", elements.size());

        for (Element element : elements) {
            Element linkEl = element.selectFirst("div.cCont_goodsSet div.goods_info div.goods_name a");
            if (Objects.nonNull(linkEl)) {
                String detailPath = linkEl.attr("href");
                log.info("linkEl: {}", linkEl.attr("href"));
                Document detailDoc = Jsoup.connect(yes24Domain + detailPath).get();
                Elements trEls = detailDoc.selectFirst("div.infoSetCont_wrap").select("table.tb_nor tbody.b_size tr");
                log.info("tr size {} : ", trEls.size());

                for (Element trEl : trEls) {
                    if ("ISBN13".equals(trEl.selectFirst("th").text())) {
                        log.info("isbn13 : {}", trEl.selectFirst("td").text());
                    } else if ("ISBN10".equals(trEl.selectFirst("th").text())) {
                        log.info("isbn : {}", trEl.selectFirst("td").text());
                    }
                }
            }
        }
        return new ArrayList<>();
    }
}
