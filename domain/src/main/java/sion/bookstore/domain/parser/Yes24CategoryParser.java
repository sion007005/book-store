package sion.bookstore.domain.parser;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class Yes24CategoryParser {
    private static final String CATEGORY_PAGE_URL = "http://www.yes24.com/Mall/Main/Book/001?CategoryNumber=";
    List<ParsedCategory> parsedCategoryList = new ArrayList<>();

    @Getter
    public enum CategoryNumber {
        KOREA_BOOK("국내도서", "001"),
        FOREIGN_BOOK("국외도서", "002");

        private String description;
        private String number;

        CategoryNumber(String description, String number) {
            this.description = description;
            this.number = number;
        }

    }

    /**
     * "http://www.yes24.com/Mall/Main/Book/001?CategoryNumber=001"
     */
    public List<ParsedCategory> parse(CategoryNumber categoryNumber) throws IOException {

        Document doc = Jsoup.connect(CATEGORY_PAGE_URL + categoryNumber.getNumber()).get();
        Elements element = doc.select("div#cateLiWrap ul");
        Elements level2LiElements = element.select("li.cate2d");

        //1. level2의 li list 가져오기
        for (Element level2Li : level2LiElements) {
            Elements level3LiElements = level2Li.select("div ul li");
            String number = parseAndAddList(level2Li, 2, categoryNumber.getNumber());

            for (Element level3Li : level3LiElements) {
                parseAndAddList(level3Li, 3, number);
            }
        }

        return parsedCategoryList;
    }

    private String parseAndAddList(Element element, int level, String parentCategoryNumber) {
        Element linkEl = element.selectFirst("a");
        String categoryName = linkEl.text();
        String categoryLink = linkEl.attr("href");
        String categoryNumber = categoryLink.substring(categoryLink.lastIndexOf("/") + 1);
//        log.info("level {} {} {} {} {} ", level, categoryName, categoryLink, categoryNumber, parentCategoryNumber);

        ParsedCategory category = new ParsedCategory(level, categoryName, categoryLink, categoryNumber, parentCategoryNumber);
        parsedCategoryList.add(category);
//        saveToFile(level, categoryName, categoryLink, categoryNumber, parentCategoryNumber);

        return categoryNumber;
    }

    public void saveToFile(int level, String name, String link, String number, String parentNumber) {
        File file = new File("D:/test/level2list.txt");
        FileWriter writer = null;

        try {
            writer = new FileWriter(file, true);
            writer.write(level + "&" + name + "&" + link + "&" + number + "&" + parentNumber + "\n");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            try {
                if(writer != null) writer.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
