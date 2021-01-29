package sion.bookstore.admin.controller.util.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sion.bookstore.domain.book.repository.Author;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.repository.Translator;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BookValidator implements Validator<Book> {
    private final HasValueValidator hasValueValidator;
    private final IsbnValidator isbnValidator;
    private final NameValidator nameValidator;
    private final NumberValidator numberValidator;

    @Override
    public void validate(Book book) {
        hasValueValidator.validate(book.getTitle(), "title");
        hasValueValidator.validate(book.getContent(), "content");
        isbnValidator.validate(book.getIsbn10(), book.getIsbn13());
        hasValueValidator.validate(book.getPublisher(), "publisher");
        numberValidator.validate(String.valueOf(book.getPrice()));
        numberValidator.validate(String.valueOf(book.getSalePrice()));

        List<Author> authors = book.getAuthors();
        for (Author author : authors) {
            nameValidator.validate(author.getName(), "author");
        }

        List<Translator> translators = book.getTranslators();
        for (Translator translator : translators) {
            nameValidator.validate(translator.getName(), "translator");
        }

    //TODO 판매상태, 출판일 검증
    }
}
