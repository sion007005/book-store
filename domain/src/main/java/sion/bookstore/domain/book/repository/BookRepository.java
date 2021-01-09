package sion.bookstore.domain.book.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BookRepository {
    Long create(Book book);
    Book findOne(Long bookId);
    void update(Book book);
//    List<Book> findAll(BookSearchCondition bookSearchCondition);
//    long countAll(BookSearchCondition condition);
}
