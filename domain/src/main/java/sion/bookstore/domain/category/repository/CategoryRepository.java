package sion.bookstore.domain.category.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import sion.bookstore.domain.category.service.CategorySearchCondition;

import java.util.List;

@Mapper
@Repository
public interface CategoryRepository {
    Long create(Category category);
    Long update(Category category);
    Long countAll(CategorySearchCondition condition);
    Category findOneById(Long categoryId);
    Category findOneWithSameOrder(Category category);
    List<Category> findAllWithSameParentId(Category category);
    List<Category> findAllCategories(CategorySearchCondition categoryCondition);
    List<Category> findAllByCategoryLevel(int level);
    List<Category> findOthersWithSameLevel(CategorySearchRange categorySearchRange);
}
