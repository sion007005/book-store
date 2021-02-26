package sion.bookstore.domain.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.BaseAuditor;
import sion.bookstore.domain.PageCondition;
import sion.bookstore.domain.category.repository.Category;
import sion.bookstore.domain.category.repository.CategoryRepository;
import sion.bookstore.domain.parser.ParsedCategory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Long create(Category category) {
        // TODO category validator

        Category existingCategory = categoryRepository.findOneWithSameOrder(category);
        if (Objects.nonNull(existingCategory)) {
            List<Category> categories = findAllWithSameParentId(category);
            categories.add(category.getOrder() - 1, category);

            for (int i = category.getOrder(); i < categories.size(); i++) {
                updateOtherOrders(category);
            }
        }

        BaseAuditor.setCreationInfo(category);
        categoryRepository.create(category);

        return category.getId();
    }

    private List<Category> findAllWithSameParentId(Category category) {
        List<Category> categories = categoryRepository.findAllWithSameParentId(category);
        return categories;
    }

    public Long update(Category category) {
        // TODO category validator
        Category existingCategory = categoryRepository.findOneById(category.getId());

        if (!existingCategory.getOrder().equals(category.getOrder())) {
            updateOtherOrders(category);
        }

        category.setCreatedAt(existingCategory.getCreatedAt());
        category.setCreatedBy(existingCategory.getCreatedBy());
        BaseAuditor.setUpdatingInfo(category);

        categoryRepository.update(category);
        return category.getId();
    }

    private void updateOtherOrders(Category category) {
        List<Category> categories = findAllWithSameParentId(category);
        categories.add(category.getOrder() - 1, category);
        int order = 1;

        for (Category one : categories) {
            if (one.getOrder().equals(order)) {
                order++;
                continue;
            }

            one.setOrder(order++);
            BaseAuditor.setUpdatingInfo(one);
            categoryRepository.update(one);
        }
    }

    public Category findOneById(Long id) {
        return categoryRepository.findOneById(id);
    }

    public Page<Category> findAllCategories(CategorySearchCondition condition) {
        List<Category> categoryList = categoryRepository.findAllCategories(condition);
        long totalElements = categoryRepository.countAll(condition);
        PageImpl<Category> categories = new PageImpl<>(categoryList, condition.getPageable(), totalElements);

        return categories;
    }

    public void createAllByParsedList(List<ParsedCategory> list) {
        Map<String, Long> categoryIdMappingList = new HashMap<>();
        categoryIdMappingList.put("001", (long) 2); // 국내도서
        categoryIdMappingList.put("002", (long) 3); // 국외도서

        int levelTwoOrder = 1;
        int levelThreeOrder = 1;

        for (ParsedCategory parsedCategory : list) {
            int order;

            if (parsedCategory.getLevel() == 2) {
                order = levelTwoOrder++;
                levelThreeOrder = 1;
            } else {
                order = levelThreeOrder++;
            }

            Category category = new Category();
            category.setParentId(categoryIdMappingList.get(parsedCategory.getParentNumber()));
            category.setName(parsedCategory.getName());
            category.setLevel(parsedCategory.getLevel());
            category.setOrder(order);
            category.setLink(parsedCategory.getLink());
            BaseAuditor.setCreationInfo(category);

            categoryRepository.create(category);
            categoryIdMappingList.put(parsedCategory.getNumber(), category.getId());
        }
    }

    public CategoryNode findAllCategoryNodes() {
        CategorySearchCondition condition = new CategorySearchCondition();
        condition.setSize(PageCondition.GET_ALL);
        List<Category> categories = categoryRepository.findAllCategories(condition);

        CategoryNodeBuilder builder = new CategoryNodeBuilder();
        CategoryNode node = builder.build(categories);

        return node;
    }
}
