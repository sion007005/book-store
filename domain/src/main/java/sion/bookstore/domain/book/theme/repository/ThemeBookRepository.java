package sion.bookstore.domain.book.theme.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.theme.service.ThemeBookSearchCondition;

import java.util.List;

@Mapper
@Repository
public interface ThemeBookRepository {
    Long create(ThemeBook mapping);
    void update(ThemeBook themeBook);
    List<Book> findBooksByThemeSectionId(ThemeBookSearchCondition condition);
    List<ThemeBook> findThemeBooksByThemeSectionId(ThemeBookSearchCondition condition);
}
