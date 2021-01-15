package sion.bookstore.domain.book.service;

import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.book.repository.Author;
import sion.bookstore.domain.book.repository.AuthorRepository;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.repository.BookRepository;
import sion.bookstore.domain.parser.OnePageParser;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private OnePageParser onePageParser;

    @Autowired
    private TranslatorService translatorService;

    @Autowired
    private BookCategoryService bookCategoryService;

    public Long create(Book book) {
        return bookRepository.create(book);
    }

    public Book findOne(Long bookId) {
        return bookRepository.findOne(bookId);
    }

    public void update(Book book) {
        bookRepository.update(book);
    }

    public void createAll(Long categoryId, List<Book> bookList) {
        for (Book book : bookList) {
            create(book);
            createAuthors(book);
            translatorService.create(book);
            bookCategoryService.create(categoryId, book.getId());
        }
    }

    private void createAuthors(Book book) {
        List<String> authors = Arrays.asList(book.getAuthors());

        for (String name : authors) {
            Author author = new Author();
            author.setBookId(book.getId());
            author.setName(name);
            author.setCreatedAt(new Date());
            author.setCreatedBy("sion");
            author.setModifiedAt(new Date());
            author.setModifiedBy("sion");
            author.setDeleted(false);

            authorRepository.create(author);
        }
    }
}
