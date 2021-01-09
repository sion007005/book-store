package sion.bookstore.domain.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.repository.BookRepository;
import sion.bookstore.domain.category.repository.Category;
import sion.bookstore.domain.parser.OnePageParser;
import sion.bookstore.domain.parser.ParsedBook;

import java.io.IOException;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OnePageParser onePageParser;

    public Long create(Book book) {
        return bookRepository.create(book);
    }

    public Book findOne(Long bookId) {
        return bookRepository.findOne(bookId);
    }

    public void update(Book book) {
        bookRepository.update(book);
    }

    public void parseAndCreate(Category category) throws IOException {
        List<ParsedBook> parsedBookList = onePageParser.parse(category.getLink());

        for (ParsedBook book : parsedBookList) {

        }

    }
}
