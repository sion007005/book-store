package sion.bookstore.domain.book;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sion.bookstore.domain.ApplicationConfiguration;
import sion.bookstore.domain.book.service.BookRegisterFacade;
import sion.bookstore.domain.category.repository.Category;
import sion.bookstore.domain.category.repository.CategoryRepository;

import java.io.IOException;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={ApplicationConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AutoBookInsertTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRegisterFacade bookRegisterFacade;

    @Test
    public void autoBookInsert() throws IOException {
        List<Category> categoryList = categoryRepository.findAllByCategoryLevel(3);

        for (Category category : categoryList) {
            String categoryPageUrl = category.getLink();
            bookRegisterFacade.register(categoryPageUrl, category.getId());
        }
    }
}
