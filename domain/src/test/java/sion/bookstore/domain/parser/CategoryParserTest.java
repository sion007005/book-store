package sion.bookstore.domain.parser;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sion.bookstore.domain.ApplicationConfiguration;
import sion.bookstore.domain.category.service.CategoryService;

import java.io.IOException;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={ApplicationConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryParserTest {
    @Autowired
    private CategoryService categoryService;

    @Test
    public void parse() throws IOException {
        String url = "http://www.yes24.com/Mall/Main/Book/001?CategoryNumber=001";
//        CategoryParser parser = new CategoryParser();
//        List<ParsedCategory> list =  parser.parse(url);

        categoryService.parseAndRegister(url);

    }

}
