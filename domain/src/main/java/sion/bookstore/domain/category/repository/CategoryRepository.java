package sion.bookstore.domain.category.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import sion.bookstore.domain.category.service.CategorySearchCondition;

import java.util.List;

@Mapper
@Repository
public interface CategoryRepository {
    Integer create(Category category);
    Category findOne(Long categoryId);
    void update(Category category);
    List<Category> findAllCategoryNode(CategorySearchCondition categoryCondition);
    List<Category> findAllByCategoryLevel(int level);
    List<Category> findAllById(Long limitId);
    long countAll(CategorySearchCondition condition);
}
