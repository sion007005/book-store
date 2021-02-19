package sion.bookstore.domain.category.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sion.bookstore.domain.ApplicationConfiguration;
import sion.bookstore.domain.category.repository.Category;
import sion.bookstore.domain.category.repository.CategoryRepository;

import java.util.List;
import java.util.Objects;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={ApplicationConfiguration.class, CategoryRepository.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryNodeBuilderTest {
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void build() {
        CategorySearchCondition condition = new CategorySearchCondition();
        condition.setSize(1000);
        List<Category> categories = categoryRepository.findAllCategories(condition);

        CategoryNodeBuilder builder = new CategoryNodeBuilder();
        CategoryNode node = builder.build(categories);

        displayNodes(node);
    }

    public void displayNodes(CategoryNode node) {
        if (Objects.isNull(node)) {
            return;
        }

        log.info("id : {}", node.getId());
        log.info("name : {}", node.getName());
        log.info("parent id: {}", (node.getParentId() != 0 ? node.getParentId() : null));
        log.info("children count : {}", node.getChildren().size());

        List<CategoryNode> childrenNodeList = node.getChildren();
        for (CategoryNode categoryNode : childrenNodeList) {
            displayNodes(categoryNode);
        }
    }
}