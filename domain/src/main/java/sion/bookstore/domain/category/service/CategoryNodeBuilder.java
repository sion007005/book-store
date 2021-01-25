package sion.bookstore.domain.category.service;

import sion.bookstore.domain.category.repository.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CategoryNodeBuilder {
    private Map<Long, CategoryNode> categoryNodeMap = new HashMap<>();
    private CategoryNode rootNode;

    public CategoryNode build(List<Category> categories) {
        for (Category category : categories) {
            CategoryNode parent = findParent(category);
//              CategoryNode node = new CategoryNode(category, parent);
            CategoryNode node = new CategoryNode(category);

            if (Objects.nonNull(parent)) {
                parent.addChild(node);
            }

            if (category.getLevel() == 0) {
                this.rootNode = node;
            }

            categoryNodeMap.put(node.getId(), node);
        }
        return findRootNode();
    }

    private CategoryNode findParent(Category category) {
        return categoryNodeMap.get(category.getParentId());
    }

    private CategoryNode findRootNode() {
        if (this.rootNode == null) {
            throw new RuntimeException("category root node is empty!");
        }
        return this.rootNode;
    }
}
