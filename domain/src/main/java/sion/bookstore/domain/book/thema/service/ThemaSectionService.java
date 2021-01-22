package sion.bookstore.domain.book.thema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.repository.BookRepository;
import sion.bookstore.domain.book.thema.repository.ThemaBook;
import sion.bookstore.domain.book.thema.repository.ThemaBookRepository;
import sion.bookstore.domain.book.thema.repository.ThemaSection;
import sion.bookstore.domain.book.thema.repository.ThemaSectionRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemaSectionService {
    private final ThemaSectionRepository themaSectionRepository;
    private final ThemaBookRepository themaBookRepository;
    private final BookRepository bookRepository;

    // TODO[DONE] 아래 두 역할을 다 해줘야 함
    //  1. 테마 섹션 저장
    //  2. ThemaBook 매핑 (* bookList보다는 bookIdList를 받아오는게 더 나은가?)
    @Transactional(readOnly = false)
    public Long create(ThemaSection themaSection, List<Book> bookList) {
        themaSectionRepository.create(themaSection);
        doMappingBooks(themaSection.getId(), bookList);

        return themaSection.getId();
    }

    public ThemaSection findOne(Long id) {
        return themaSectionRepository.findOne(id);
    }

    //TODO[DONE] 테마 섹션 한개 + 속한 책들 가져오기
    public ThemaSection findOneWithBooks(Long id) {
        ThemaSection themaSection = themaSectionRepository.findOne(id);
        List<Book> bookList = getBookListBySectionId(id);
        themaSection.setBooks(bookList);

        return themaSection;
    }

    public List<ThemaSection> findAll(ThemaSectionSearchCondition themaSectionSearchCondition) {
        return themaSectionRepository.findAll(themaSectionSearchCondition);
    }

    //TODO[DONE]
    // condition 안에는 아무 조건이 들어있지 않아야 함
    public List<ThemaSection> findAllWithBooks(ThemaSectionSearchCondition condition) {
        List<ThemaSection> themaSectionList = themaSectionRepository.findAll(condition);

        for (ThemaSection themaSection : themaSectionList) {
            List<Book> bookList = getBookListBySectionId(themaSection.getId());
            themaSection.setBooks(bookList);
        }

        return themaSectionList;
    }

    private List<Book> getBookListBySectionId(Long id) {
        ThemaBookSearchCondition condition = new ThemaBookSearchCondition();
        condition.setThemaSectionId(id);
        List<ThemaBook> themaBookList = themaBookRepository.findBooksByThemaSectionId(condition);

        // TODO join 쿼리 이용해서 for문 돌지않게끔 하기
        List<Book> bookList = new ArrayList<>();
        for (ThemaBook themaBook : themaBookList) {
            Book book = bookRepository.findOne(themaBook.getBookId());
            bookList.add(book);
        }

        return bookList;
    }

    //TODO[DONE]
    // 1. ThemaSection 업데이트
    // 2. ThemaBook 삭제(deleted -> true)
    // 3. 신규 ThemaBook(새로 등록시켜준 책들) 등록
    @Transactional
    public void update(ThemaSection themaSection, List<Book> bookList) {
        themaSectionRepository.update(themaSection);

        ThemaBookSearchCondition condition = new ThemaBookSearchCondition();
        condition.setThemaSectionId(themaSection.getId());
        List<ThemaBook> themaBookMappingList = themaBookRepository.findBooksByThemaSectionId(condition);

        for (ThemaBook themaBook : themaBookMappingList) {
            // TODO db 값도 바꿔야지!
            themaBook.setDeleted(true);
        }

        doMappingBooks(themaSection.getId(), bookList);
    }
    // 데이터를 바꾸는 로직이므로, 공개 범위 고민!
    private void doMappingBooks(Long themaSectionId, List<Book> bookList) {
        for (Book book : bookList) {
            ThemaBook mapping = new ThemaBook();
            mapping.setThemaSectionId(themaSectionId);
            mapping.setBookId(book.getId());
            mapping.setCreatedAt(new Date());
            mapping.setCreatedBy("sion");
            mapping.setModifiedAt(new Date());
            mapping.setModifiedBy("sion");
            mapping.setDeleted(false);

            themaBookRepository.create(mapping);
        }
    }

}
