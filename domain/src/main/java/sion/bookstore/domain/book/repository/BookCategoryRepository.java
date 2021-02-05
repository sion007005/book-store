package sion.bookstore.domain.book.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import sion.bookstore.domain.category.repository.Category;

import java.util.List;

@Mapper
@Repository
public interface BookCategoryRepository {
    Long create(BookCategory mapping);
    List<Category> findCategoriesByBookId(Long bookId);
}
