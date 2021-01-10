package sion.bookstore.domain.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.parser.OnePageParser;
import sion.bookstore.domain.parser.ParsedBook;

import java.io.IOException;
import java.util.List;

@Service
public class BookRegisterFacade {
    @Autowired
    private OnePageParser onePageParser;

    @Autowired
    private KaKaoBookMaker kaKaoBookMaker;

    @Autowired
    private BookService bookService;


    public void register(String url, Long categoryId) throws IOException {
        List<ParsedBook> parsedBookList = onePageParser.parse(url);
        List<Book> bookList = kaKaoBookMaker.requestBookList(parsedBookList);
        bookService.createAll(categoryId, bookList);
    }
}
