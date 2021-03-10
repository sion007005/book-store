package sion.bookstore.domain.book.migration;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sion.bookstore.domain.book.repository.Author;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.repository.Translator;
import sion.bookstore.domain.book.service.AuthorService;
import sion.bookstore.domain.book.service.BookSearchCondition;
import sion.bookstore.domain.book.service.BookService;
import sion.bookstore.domain.book.service.TranslatorService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookMigrationService {
    private final BookService bookService;
    private final AuthorService authorService;
    private final TranslatorService translatorService;

    public Page<Book> getBookPage(BookSearchCondition condition) {
        Page<Book> bookList = bookService.findAll(condition);

        return bookList;
    }

    public List<Book> setAuthorAndTranslator(Page<Book> bookList) {
        List<Book> changedBookList = new ArrayList<>();

        for (Book book : bookList) {
            List<Author> authors = authorService.findAllByBookId(book.getId());
            book.setAuthor1(authors.get(0).getName());

            if (authors.size() > 1) {
                book.setAuthor2(authors.get(1).getName());
            }

            if (authors.size() > 2) {
                book.setAuthor3(authors.get(2).getName());
            }

            List<Translator> translators = translatorService.findAllByBookId(book.getId());
            if (translators.size() == 0) {
                changedBookList.add(book);
                continue;
            }

            book.setTranslator1(translators.get(0).getName());

            if (translators.size() > 1) {
                book.setTranslator2(translators.get(1).getName());
            }

            if (translators.size() > 2) {
                book.setTranslator3(translators.get(2).getName());
            }

            changedBookList.add(book);
        }

        return changedBookList;
    }

    public void updateBookList(List<Book> bookList) {
        for (Book book : bookList) {
            bookService.update(book);
        }
    }
}
