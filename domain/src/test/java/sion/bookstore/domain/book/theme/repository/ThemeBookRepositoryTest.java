package sion.bookstore.domain.book.theme.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sion.bookstore.domain.ApplicationConfiguration;
import sion.bookstore.domain.book.theme.service.ThemeBookSearchCondition;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={ApplicationConfiguration.class, ThemeBookRepository.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ThemeBookRepositoryTest {
    @Autowired
    private ThemeBookRepository themeBookRepository;

    @Test
    public void test() {
        ThemeBook themeBook = new ThemeBook();
        themeBook.setThemaSectionId(1L);
        themeBook.setBookId(2L);
        themeBook.setCreatedAt(new Date());
        themeBook.setCreatedBy("sion");
        themeBook.setModifiedAt(new Date());
        themeBook.setModifiedBy("sion");
        themeBook.setDeleted(false);

        themeBookRepository.create(themeBook);

        ThemeBookSearchCondition condition = new ThemeBookSearchCondition();
        condition.setThemaSectionId(1L);

        List<ThemeBook> books = themeBookRepository.findThemeBooksByThemeSectionId(condition);

        assertEquals(2, books.size());
    }

}