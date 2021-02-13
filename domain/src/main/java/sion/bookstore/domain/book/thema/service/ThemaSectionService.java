package sion.bookstore.domain.book.thema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.service.BookService;
import sion.bookstore.domain.book.thema.repository.ThemaBook;
import sion.bookstore.domain.book.thema.repository.ThemaBookRepository;
import sion.bookstore.domain.book.thema.repository.ThemaSection;
import sion.bookstore.domain.book.thema.repository.ThemaSectionRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ThemaSectionService {
    private final ThemaSectionRepository themaSectionRepository;
    private final ThemaBookRepository themaBookRepository;
    private final BookService bookService;

    // TODO[DONE] 아래 두 역할을 다 해줘야 함
    //  1. 테마 섹션 저장
    //  2. ThemaBook 매핑
    @Transactional(readOnly = false)
    public Long createAndBookMapping(ThemaSection themaSection) {
        themaSection.setCreatedAt(new Date());
        themaSection.setCreatedBy("tester");
        themaSection.setModifiedAt(new Date());
        themaSection.setModifiedBy("tester");
        themaSection.setDeleted(false);

        themaSectionRepository.create(themaSection);
        doMappingBooks(themaSection);

        return themaSection.getId();
    }

    public ThemaSection findOne(Long id) {
        return themaSectionRepository.findOne(id);
    }

    //TODO[DONE] 테마 섹션 한개 + 속한 책들 가져오기
    public ThemaSection findOneWithBooks(Long id) {
        ThemaSection themaSection = themaSectionRepository.findOne(id);

        if (Objects.nonNull(themaSection)) {
            List<Book> bookList = getBookListBySectionId(id);
            themaSection.setBooks(bookList);
        }

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
        // TODO[DONE] join 쿼리 이용해서 for문 돌지않게끔 하기
        List<Book> bookList = themaBookRepository.findBooksByThemaSectionId(condition);

        // TODO CHECK 80번라인 만으로는 author정보 못 받아와서, 아래에서 author 정보를 담은 책으로 새로 받아옴
        List<Book> bookListWithAuthors = new ArrayList<>();
        for (Book book : bookList) {
            Book bookWithAuthor = bookService.findOneById(book.getId());
            bookListWithAuthors.add(bookWithAuthor);
        }

        return bookListWithAuthors;
    }

    //TODO[DONE]
    // 1. ThemaSection 업데이트
    // 2. ThemaBook 삭제(deleted -> true)
    // 3. 신규 ThemaBook(새로 등록시켜준 책들) 등록
    @Transactional
    public void update(ThemaSection themaSection) {
        themaSection.setModifiedAt(new Date());
        themaSection.setModifiedBy("tester");
        themaSection.setDeleted(false);
        themaSectionRepository.update(themaSection);

        deleteBooksMappedByThema(themaSection.getId());

        doMappingBooks(themaSection);
    }
    // 데이터를 바꾸는 로직이므로, 공개 범위 고민!
    private void doMappingBooks(ThemaSection themaSection) {
        List<Book> bookList = themaSection.getBooks();
        for (Book book : bookList) {
            ThemaBook mapping = new ThemaBook();
            mapping.setThemaSectionId(themaSection.getId());
            mapping.setBookId(book.getId());
            mapping.setCreatedAt(new Date());
            mapping.setCreatedBy("sion");
            mapping.setModifiedAt(new Date());
            mapping.setModifiedBy("sion");
            mapping.setDeleted(false);

            themaBookRepository.create(mapping);
        }
    }

    private void deleteBooksMappedByThema(Long themaSectionId) {
        ThemaBookSearchCondition condition = new ThemaBookSearchCondition();
        condition.setThemaSectionId(themaSectionId);
        List<ThemaBook> themaBookMappingList = themaBookRepository.findThemaBooksByThemaSectionId(condition);

        for (ThemaBook themaBook : themaBookMappingList) {
            themaBook.setModifiedAt(new Date());
            themaBook.setModifiedBy("tester");
            themaBook.setDeleted(true);

            themaBookRepository.update(themaBook);
        }
    }

    public void delete(Long id) {
        ThemaSection themaSection = themaSectionRepository.findOne(id);
        themaSection.setModifiedAt(new Date());
        themaSection.setModifiedBy("tester");
        themaSection.setDeleted(true);
        themaSectionRepository.update(themaSection);
        // TODO CHECK 굳이 끊어줄 필요가 없나? 지난 테마를 불러올 수도 있으니까..?
        deleteBooksMappedByThema(id);
    }

}
