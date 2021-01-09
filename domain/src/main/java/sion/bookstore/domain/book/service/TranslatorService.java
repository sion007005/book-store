package sion.bookstore.domain.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.book.repository.Translator;
import sion.bookstore.domain.book.repository.TranslatorRepository;

@Service
public class TranslatorService {
    @Autowired
    private TranslatorRepository translatorRepository;

    public Long create(Translator translator) {
        return translatorRepository.create(translator);
    }

    public void update(Translator translator) {
        translatorRepository.update(translator);
    }
}
