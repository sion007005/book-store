package sion.bookstore.domain.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.category.repository.Category;
import sion.bookstore.domain.category.repository.CategoryRepository;
import sion.bookstore.domain.parser.CategoryParser;
import sion.bookstore.domain.parser.ParsedCategory;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryParser categoryParser;

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

    public void parseAndRegister(String url) throws IOException {
        Map<String, Long> categoryIdMappingList = new HashMap<>();
        categoryIdMappingList.put("001", (long) 2); // 국내도서
        categoryIdMappingList.put("002", (long) 3); // 국외도서

        List<ParsedCategory> list = categoryParser.parse(url);

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
            category.setCreatedAt(new Date());
            category.setCreatedBy("sion");
            category.setModifiedAt(new Date());
            category.setModifiedBy("sion");
            category.setDeleted(false);

            categoryRepository.create(category);
            categoryIdMappingList.put(parsedCategory.getNumber(), category.getId());
        }
    }
}
