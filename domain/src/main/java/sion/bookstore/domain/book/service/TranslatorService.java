package sion.bookstore.domain.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.repository.Translator;
import sion.bookstore.domain.book.repository.TranslatorRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TranslatorService {
    private final TranslatorRepository translatorRepository;

    public Long create(Translator translator) {
        return translatorRepository.create(translator);
    }

    public void create(Book book) {
        List<Translator> translators = book.getTranslators();

        for (Translator translator : translators) {
            translator.setBookId(book.getId());
            translator.setName(translator.getName());
            translator.setCreatedAt(new Date());
            translator.setCreatedBy("sion");
            translator.setModifiedAt(new Date());
            translator.setModifiedBy("sion");
            translator.setDeleted(false);

            translatorRepository.create(translator);
        }
    }

    public void update(Book book) {
        List<Translator> translators = book.getTranslators();
        for (Translator translator : translators) {
            translator.setBookId(book.getId());
            translator.setCreatedAt(book.getCreatedAt());
            translator.setCreatedBy(book.getCreatedBy());
            translator.setModifiedAt(new Date());
            translator.setModifiedBy("sion");
            translator.setDeleted(false);

            translatorRepository.update(translator);
        }
    }

    public List<Translator> findAllByBookId(Long bookId) {
        return translatorRepository.findAllByBookId(bookId);
    }

}
