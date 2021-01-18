package sion.bookstore.domain.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.repository.Translator;
import sion.bookstore.domain.book.repository.TranslatorRepository;

import java.util.Arrays;
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
        List<String> translators = Arrays.asList(book.getTranslators());

        for (String name : translators) {
            Translator translator = new Translator();
            translator.setBookId(book.getId());
            translator.setName(name);
            translator.setCreatedAt(new Date());
            translator.setCreatedBy("sion");
            translator.setModifiedAt(new Date());
            translator.setModifiedBy("sion");
            translator.setDeleted(false);

            create(translator);
        }
    }

    public void update(Translator translator) {
        translatorRepository.update(translator);
    }

}
