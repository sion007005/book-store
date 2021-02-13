package sion.bookstore.domain.book.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import sion.bookstore.domain.ApplicationConfiguration;
import sion.bookstore.domain.book.repository.Author;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={ApplicationConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@WebAppConfiguration
public class BookServiceTest {
    @Autowired
    private BookCategoryService bookCategoryService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;

    @Test
    public void test() {
//        BookCategorySearchCondition condition = new BookCategorySearchCondition();
//        condition.setCategoryId(6L);
//        condition.setPage(2);
//
//        List<Book> bookList = new ArrayList<>();
//        List<BookCategory> bookCategoryList = bookCategoryService.findBooksByCategoryId(condition);
//
//        for (BookCategory bookCategory : bookCategoryList) {
//            Long bookId = bookCategory.getBookId();
//            Book book = bookRepository.findOne(bookId);
//            log.info("책 제목: {}",book.getTitle());
//
//            bookList.add(book);
//        }
//
//        assertEquals(20, bookList.size());
    }

    @Test
    public void findAllByAuthorTest() {
        AuthorSearchCondition condition = new AuthorSearchCondition();
        condition.setName("정홍");

        List<Author> authors = authorService.findAllByName(condition);
        List<Book> bookList = new ArrayList<>();

        for (Author author : authors) {
            Book book = bookService.findOneById(author.getBookId());
            bookList.add(book);
        }

        for (Book book : bookList) {
            log.info("책 이름: {}", book.getTitle());
        }

    }

}