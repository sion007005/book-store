package sion.bookstore.domain.book.thema.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sion.bookstore.domain.ApplicationConfiguration;
import sion.bookstore.domain.book.thema.service.ThemaBookSearchCondition;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={ApplicationConfiguration.class, ThemaBookRepository.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ThemaBookRepositoryTest {
    @Autowired
    private ThemaBookRepository themaBookRepository;

    @Test
    public void test() {
        ThemaBook themaBook = new ThemaBook();
        themaBook.setThemaSectionId(1L);
        themaBook.setBookId(2L);
        themaBook.setCreatedAt(new Date());
        themaBook.setCreatedBy("sion");
        themaBook.setModifiedAt(new Date());
        themaBook.setModifiedBy("sion");
        themaBook.setDeleted(false);

        themaBookRepository.create(themaBook);

        ThemaBookSearchCondition condition = new ThemaBookSearchCondition();
        condition.setThemaSectionId(1L);

        List<ThemaBook> books = themaBookRepository.findBooksByThemaSectionId(condition);

        assertEquals(2, books.size());
    }

}