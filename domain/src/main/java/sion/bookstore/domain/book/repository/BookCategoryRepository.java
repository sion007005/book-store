package sion.bookstore.domain.book.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BookCategoryRepository {
    Long create(BookCategory mapping);
//    List<BookCategory> findBooksByCategoryId(BookCategorySearchCondition condition);
}
