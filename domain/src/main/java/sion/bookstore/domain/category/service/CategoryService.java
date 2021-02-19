package sion.bookstore.domain.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.BaseAuditor;
import sion.bookstore.domain.category.repository.Category;
import sion.bookstore.domain.category.repository.CategoryRepository;
import sion.bookstore.domain.category.repository.CategorySearchRange;
import sion.bookstore.domain.parser.ParsedCategory;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Long create(Category category) {
        // TODO category validator

        Category existingCategory = categoryRepository.findOneWithSameOrder(category);
        if (Objects.nonNull(existingCategory)) {
            CategorySearchRange range = new CategorySearchRange(category.getParentId(), category.getOrder());
            changeNextOrders(range);
        }

        BaseAuditor.setCreationInfo(category);
        categoryRepository.create(category);

        return category.getId();
    }

    public Long update(Category category) {
        // TODO category validator
        Category existingCategory = categoryRepository.findOneById(category.getId());

        if (!existingCategory.getOrder().equals(category.getOrder())) {
            CategorySearchRange range = new CategorySearchRange(category.getParentId(), existingCategory.getOrder(), category.getOrder());
            changeNextOrders(range);
        }

        category.setCreatedAt(existingCategory.getCreatedAt());
        category.setCreatedBy(existingCategory.getCreatedBy());
        BaseAuditor.setUpdatingInfo(category);

        categoryRepository.update(category);
        return category.getId();
    }

    private void changeNextOrders(CategorySearchRange range) {
        if (!range.needPlusOrder()) {
            range.setStart(range.getStart() + 1);
            range.setEnd(range.getEnd() + 1);
        }

        List<Category> categories = findOthersWithSameLevel(range);

        for (Category category : categories) {
            Integer existingOrder = category.getOrder();

            if (range.needPlusOrder()) {
                category.setOrder(++existingOrder);
            } else {
                category.setOrder(--existingOrder);
            }

            BaseAuditor.setUpdatingInfo(category);
            categoryRepository.update(category);
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
        condition.setSize(1000);
        List<Category> categories = categoryRepository.findAllCategories(condition);

        CategoryNodeBuilder builder = new CategoryNodeBuilder();
        CategoryNode node = builder.build(categories);

        return node;
    }

    private List<Category> findOthersWithSameLevel(CategorySearchRange form) {
        List<Category> categories = categoryRepository.findOthersWithSameLevel(form);
        return categories;
    }
}
