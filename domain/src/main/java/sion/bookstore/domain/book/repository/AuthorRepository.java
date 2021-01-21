package sion.bookstore.domain.book.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import sion.bookstore.domain.book.service.AuthorSearchCondition;

import java.util.List;

@Mapper
@Repository
public interface AuthorRepository {
    Long create(Author author);
    void update(Author author);
    List<Author> findAllByName(AuthorSearchCondition condition);
}
