package sion.bookstore.domain.parser;

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
public class CategoryParser {
    List<ParsedCategory> parsedCategoryList = new ArrayList<>();

    /**
     * "http://www.yes24.com/Mall/Main/Book/001?CategoryNumber=001"
     */
    public List<ParsedCategory> parse(String url) throws IOException {

        Document doc = Jsoup.connect(url).get();
        Elements element = doc.select("div#cateLiWrap ul");
        Elements level2LiElements = element.select("li.cate2d");

        //1. level2의 li list 가져오기
        for (Element level2Li : level2LiElements) {
            Elements level3LiElements = level2Li.select("div ul li");
            String categoryNumber = parseAndAddList(level2Li, 2, "001");

            for (Element level3Li : level3LiElements) {
                parseAndAddList(level3Li, 3, categoryNumber);
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
