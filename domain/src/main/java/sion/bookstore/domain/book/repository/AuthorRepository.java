package sion.bookstore.domain.book.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AuthorRepository {
    Long create(Author author);
    void update(Author author);
//    List<Author> findAll(AuthorSearchCondition authorSearchCondition);
}
