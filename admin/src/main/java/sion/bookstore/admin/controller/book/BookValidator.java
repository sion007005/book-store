package sion.bookstore.admin.controller.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sion.bookstore.domain.book.repository.Author;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.repository.Translator;
import sion.bookstore.domain.utils.validator.EngKorStringValidator;
import sion.bookstore.domain.utils.validator.HasValueValidator;
import sion.bookstore.domain.utils.validator.NumberValidator;
import sion.bookstore.domain.utils.validator.Validator;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class BookValidator implements Validator<Book> {
    private final HasValueValidator hasValueValidator;
    private final IsbnValidator isbnValidator;
    private final EngKorStringValidator engKorStringValidator;
    private final NumberValidator numberValidator;

    @Override
    public void validate(Book book, String type) {
        hasValueValidator.validate(book.getTitle(), "title");
        hasValueValidator.validate(book.getContent(), "content");
        hasValueValidator.validate(book.getPublisher(), "publisher");
        numberValidator.validate(String.valueOf(book.getPrice()), "price");
        numberValidator.validate(String.valueOf(book.getSalePrice()), "salePrice");

        List<Author> authors = book.getAuthors();
        for (Author author : authors) {
            engKorStringValidator.validate(author.getName(), "author");
        }

        List<Translator> translators = book.getTranslators();
        for (Translator translator : translators) {
            engKorStringValidator.validate(translator.getName(), "translator");
        }

        isbnValidator.validate(Arrays.asList(book.getIsbn10(), book.getIsbn13()), "isbn number");



        //TODO 판매상태
    }
}
