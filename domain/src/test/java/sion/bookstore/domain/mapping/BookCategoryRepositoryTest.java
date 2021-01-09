package sion.bookstore.domain.mapping;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sion.bookstore.domain.ApplicationConfiguration;
import sion.bookstore.domain.book.repository.BookCategory;
import sion.bookstore.domain.book.repository.BookCategoryRepository;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={ApplicationConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookCategoryRepositoryTest {
    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Test
    public void create() {
        Long categoryId = 10L;
        Long bookId = 1000L;
        BookCategory mapping = new BookCategory();
        mapping.setCategoryId(categoryId);
        mapping.setBookId(bookId);

        bookCategoryRepository.create(mapping);
    }

}
