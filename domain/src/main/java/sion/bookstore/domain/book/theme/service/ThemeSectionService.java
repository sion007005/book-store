package sion.bookstore.domain.book.theme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sion.bookstore.domain.BaseAuditor;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.service.BookService;
import sion.bookstore.domain.book.theme.repository.ThemeBook;
import sion.bookstore.domain.book.theme.repository.ThemeBookRepository;
import sion.bookstore.domain.book.theme.repository.ThemeSection;
import sion.bookstore.domain.book.theme.repository.ThemeSectionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ThemeSectionService {
    private final ThemeSectionRepository themeSectionRepository;
    private final ThemeBookRepository themeBookRepository;
    private final BookService bookService;

    @Transactional(readOnly = false)
    public Long createAndBookMapping(ThemeSection themeSection) {
        BaseAuditor.setCreationInfo(themeSection);
        themeSectionRepository.create(themeSection);
        doMappingBooks(themeSection);

        return themeSection.getId();
    }

    public ThemeSection findOne(Long id) {
        return themeSectionRepository.findOne(id);
    }

    public ThemeSection findOneWithBooks(Long id) {
        ThemeSection themeSection = themeSectionRepository.findOne(id);

        if (Objects.nonNull(themeSection)) {
            List<Book> bookList = getBookListBySectionId(id);
            themeSection.setBooks(bookList);
        }

        return themeSection;
    }

    public List<ThemeSection> findAll(ThemeSectionSearchCondition themeSectionSearchCondition) {
        return themeSectionRepository.findAll(themeSectionSearchCondition);
    }

    //TODO[DONE]
    // condition 안에는 아무 조건이 들어있지 않아야 함
    public List<ThemeSection> findAllWithBooks(ThemeSectionSearchCondition condition) {
        List<ThemeSection> themeSectionList = themeSectionRepository.findAll(condition);

        for (ThemeSection themeSection : themeSectionList) {
            List<Book> bookList = getBookListBySectionId(themeSection.getId());
            themeSection.setBooks(bookList);
        }

        return themeSectionList;
    }

    private List<Book> getBookListBySectionId(Long id) {
        ThemeBookSearchCondition condition = new ThemeBookSearchCondition();
        condition.setThemaSectionId(id);
        // TODO[DONE] join 쿼리 이용해서 for문 돌지않게끔 하기
        List<Book> bookList = themeBookRepository.findBooksByThemeSectionId(condition);

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
    public void update(ThemeSection themeSection) {
        BaseAuditor.setUpdatingInfo(themeSection);
        themeSectionRepository.update(themeSection);
        deleteBooksMappedByThema(themeSection.getId());
        doMappingBooks(themeSection);
    }

    private void doMappingBooks(ThemeSection themeSection) {
        List<Book> bookList = themeSection.getBooks();
        for (Book book : bookList) {
            ThemeBook mapping = new ThemeBook();
            mapping.setThemaSectionId(themeSection.getId());
            mapping.setBookId(book.getId());
            BaseAuditor.setCreationInfo(themeSection);

            themeBookRepository.create(mapping);
        }
    }

    private void deleteBooksMappedByThema(Long themaSectionId) {
        ThemeBookSearchCondition condition = new ThemeBookSearchCondition();
        condition.setThemaSectionId(themaSectionId);
        List<ThemeBook> themeBookMappingList = themeBookRepository.findThemeBooksByThemeSectionId(condition);

        for (ThemeBook themeBook : themeBookMappingList) {
            BaseAuditor.setDeletionInfo(themeBook);
            themeBookRepository.update(themeBook);
        }
    }

    public void delete(Long id) {
        ThemeSection themeSection = themeSectionRepository.findOne(id);
        BaseAuditor.setDeletionInfo(themeSection);
        themeSectionRepository.update(themeSection);
        // TODO CHECK 굳이 끊어줄 필요가 없나? 지난 테마를 불러올 수도 있으니까..?
        deleteBooksMappedByThema(id);
    }

}
