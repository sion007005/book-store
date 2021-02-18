package sion.bookstore.admin.controller.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.utils.validator.EngKorStringValidator;
import sion.bookstore.domain.utils.validator.HasValueValidator;
import sion.bookstore.domain.utils.validator.NumberValidator;
import sion.bookstore.domain.utils.validator.Validator;

import java.util.Arrays;
import java.util.Objects;

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
        engKorStringValidator.validate(book.getAuthor1(), "author1");
        numberValidator.validate(String.valueOf(book.getPrice()), "price");
        numberValidator.validate(String.valueOf(book.getSalePrice()), "salePrice");

        if (Objects.nonNull(book.getAuthor2())) {
            engKorStringValidator.validate(book.getAuthor2(), "author2");
        }

        if (Objects.nonNull(book.getAuthor3())) {
            engKorStringValidator.validate(book.getAuthor3(), "author3");
        }

        if (Objects.nonNull(book.getTranslator1())) {
            engKorStringValidator.validate(book.getTranslator1(), "getTranslator1");
        }

        if (Objects.nonNull(book.getTranslator2())) {
            engKorStringValidator.validate(book.getTranslator2(), "getTranslator2");
        }

        if (Objects.nonNull(book.getTranslator3())) {
            engKorStringValidator.validate(book.getTranslator3(), "getTranslator3");
        }
//        List<Author> authors = book.getAuthors();
//        for (Author author : authors) {
//            engKorStringValidator.validate(author.getName(), "author");
//        }

//        List<Translator> translators = book.getTranslators();
//        for (Translator translator : translators) {
//            engKorStringValidator.validate(translator.getName(), "translator");
//        }

        isbnValidator.validate(Arrays.asList(book.getIsbn10(), book.getIsbn13()), "isbn number");


    }
}
