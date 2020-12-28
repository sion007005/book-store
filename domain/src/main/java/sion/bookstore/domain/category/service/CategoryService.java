package sion.bookstore.domain.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import sion.bookstore.domain.category.repository.Category;
import sion.bookstore.domain.category.repository.CategoryRepository;

import java.util.List;

public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Integer create(Category category) {
        return categoryRepository.create(category);
    }

    public Category findOne(Long id) {
        return categoryRepository.findOne(id);
    }

    public Page<Category> findPageByCondition(CategorySearchCondition condition) {
        List<Category> categoryList = categoryRepository.findAll(condition);
        long totalElements = categoryRepository.countAll(condition);
        return new PageImpl<>(categoryList, condition.getPageable(), totalElements);
    }
}
