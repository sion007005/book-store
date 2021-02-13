package sion.bookstore.domain.book.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import sion.bookstore.domain.book.service.BookSearchCondition;

import java.util.List;

@Mapper
@Repository
public interface BookRepository {
    Long create(Book book);
    Book findOneById(Long bookId);
    void update(Book book);
    List<Book> findAll(BookSearchCondition bookSearchCondition);
    Long countAll(BookSearchCondition condition);
}
