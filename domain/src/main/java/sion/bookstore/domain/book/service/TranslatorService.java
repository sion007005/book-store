package sion.bookstore.domain.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TranslatorService {
//    private final TranslatorRepository translatorRepository;
//
//    public Long create(Translator translator) {
//        return translatorRepository.create(translator);
//    }
//
//    public void create(Book book) {
//        List<Translator> translators = book.getTranslators();
//
//        for (Translator translator : translators) {
//            translator.setBookId(book.getId());
//            translator.setName(translator.getName());
//            translator.setCreatedAt(new Date());
//            translator.setCreatedBy(UserContext.get().getUserEmail());
//            translator.setModifiedAt(new Date());
//            translator.setModifiedBy(UserContext.get().getUserEmail());
//            translator.setDeleted(false);
//
//            translatorRepository.create(translator);
//        }
//    }
//
//    public void update(Book book) {
//        List<Translator> translators = book.getTranslators();
//        for (Translator translator : translators) {
//            translator.setBookId(book.getId());
//            translator.setCreatedAt(book.getCreatedAt());
//            translator.setCreatedBy(book.getCreatedBy());
//            translator.setModifiedAt(new Date());
//            translator.setModifiedBy(UserContext.get().getUserEmail());
//            translator.setDeleted(false);
//
//            translatorRepository.update(translator);
//        }
//    }
//
//    public void updateNewMapping(Book book) {
//        delete(book.getId());
//        create(book);
//    }
//
//    public void delete(Long bookId) {
//        List<Translator> translators = findAllByBookId(bookId);
//        for (Translator translator : translators) {
//            translator.setModifiedAt(new Date());
//            translator.setModifiedBy(UserContext.get().getUserEmail());
//            translator.setDeleted(true);
//
//            translatorRepository.update(translator);
//        }
//    }
//
//    public List<Translator> findAllByBookId(Long bookId) {
//        return translatorRepository.findAllByBookId(bookId);
//    }

}
