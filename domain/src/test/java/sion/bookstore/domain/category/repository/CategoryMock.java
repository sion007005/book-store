package sion.bookstore.domain.category.repository;

import java.util.Date;

public class CategoryMock {
    public static Category getCategory(String name, Long parentId, Integer level) {
        Category category = new Category();
        category.setParentId(parentId);
        category.setName(name);
        category.setLevel(level);
        category.setOrder(1);
        category.setCreatedAt(new Date());
        category.setCreatedBy("test");
        category.setModifiedAt(new Date());
        category.setModifiedBy("test");
        category.setDeleted(false);

        return category;
    }
}
