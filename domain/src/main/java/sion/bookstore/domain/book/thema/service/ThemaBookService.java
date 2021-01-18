package sion.bookstore.domain.book.thema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.book.thema.repository.ThemaBook;
import sion.bookstore.domain.book.thema.repository.ThemaBookRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemaBookService {
    private final ThemaBookRepository themaBookRepository;

    public Long create(Long themaSectionId, Long bookId) {
        ThemaBook mapping = new ThemaBook();
        mapping.setThemaSectionId(themaSectionId);
        mapping.setBookId(bookId);
        mapping.setCreatedAt(new Date());
        mapping.setCreatedBy("sion");
        mapping.setModifiedAt(new Date());
        mapping.setModifiedBy("sion");
        mapping.setDeleted(false);

        themaBookRepository.create(mapping);

        return mapping.getId();
    }

    public List<ThemaBook> findBooksByThemaSectionId(ThemaBookSearchCondition condition) {
        return themaBookRepository.findBooksByThemaSectionId(condition);
    }

}
