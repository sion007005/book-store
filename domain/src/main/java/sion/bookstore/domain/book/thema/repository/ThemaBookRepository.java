package sion.bookstore.domain.book.thema.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.thema.service.ThemaBookSearchCondition;

import java.util.List;

@Mapper
@Repository
public interface ThemaBookRepository {
    Long create(ThemaBook mapping);
    void update(ThemaBook themaBook);
    List<Book> findBooksByThemaSectionId(ThemaBookSearchCondition condition);
    List<ThemaBook> findThemaBooksByThemaSectionId(ThemaBookSearchCondition condition);
}
