package sion.bookstore.domain.book.service;

import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.parser.ParsedBook;

import java.util.List;

public interface BookMaker {
    List<Book> makeBookList(List<ParsedBook> list);
    Book makeBook();
}
