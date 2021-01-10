package sion.bookstore.domain.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.repository.BookRepository;
import sion.bookstore.domain.parser.OnePageParser;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OnePageParser onePageParser;

    @Autowired
    private AuthorService authorService;

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
            authorService.create(book);
            translatorService.create(book);
            bookCategoryService.create(categoryId, book.getId());
        }

    }
}
