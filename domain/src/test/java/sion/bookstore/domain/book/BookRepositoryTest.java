package sion.bookstore.domain.book;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import sion.bookstore.domain.ApplicationConfiguration;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.repository.BookRepository;
import sion.bookstore.domain.book.service.BookSearchCondition;
import sion.bookstore.domain.category.repository.Category;
import sion.bookstore.domain.category.repository.CategoryRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;

@Slf4j
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes={ApplicationConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findOne() {
        Book expected = BookMock.getBook("좋은 책", "1234567891", "1234567891111");
        bookRepository.create(expected);
        Long id = expected.getId();

        Book actual = bookRepository.findOneById(id);

        assertEquals(expected.getTitle(), actual.getTitle());
    }

    @Test
    public void update() {
        Book book = BookMock.getBook("updateTest", "1098765432", "1098765432111");
        bookRepository.create(book);

        Book expected = bookRepository.findOneById(book.getId());
        expected.setTitle("updatedTitle");
        bookRepository.update(expected);

        Book actual = bookRepository.findOneById(expected.getId());

        log.info("바꾸기 전 책의 현재 제목: {}", expected.getTitle());
        log.info("바꾼 후 책의 현재 제목: {}", actual.getTitle());

        assertEquals(expected, actual);
    }

    @Test
    public void findAll() {
        BookSearchCondition condition = new BookSearchCondition();
        condition.setTitle("엄마");
        condition.setSize(100);
//        condition.setName("이건");
//        condition.setCategoryId(9L);
//        condition.setIsbn("9788983711892");
//        condition.setOrderType("price");

        List<Book> bookList = bookRepository.findAll(condition);

        for (Book book : bookList) {
            log.info("book.title : {}", book.getTitle());
            log.info("출판년도: {}", book.getPublishedAt());
            log.info("가격: {}", book.getSalePrice());

//            List<Author> authors = book.getAuthors();
//            for (Author author : authors) {
//                log.info("book.author: {}", author.getName());
//            }
//
//            List<Translator> trnaslators = book.getTranslators();
//            for (Translator trnaslator : trnaslators) {
//                log.info("book.translator: {}", trnaslator.getName());
//            }
        }
    }

    /**
     * 카테고리 별 책 목록 1페이지 파싱해오기
     */
    @Test
    public void getPasredBookListByCategory() {
        List<Category> categoryList = categoryRepository.findAllByCategoryLevel(3);
    }
}
