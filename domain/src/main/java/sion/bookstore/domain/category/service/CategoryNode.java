package sion.bookstore.domain.category.service;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import sion.bookstore.domain.category.repository.Category;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryNode {
    private Long id;
    private String name;
    private Integer level;
    private Integer order;
    private Long parentId;
    private List<CategoryNode> children = new ArrayList<>();

    public CategoryNode(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.level = category.getLevel();
        this.order = category.getOrder();
        this.parentId = category.getParentId();
    }
    public boolean getHasChild() {
        return !CollectionUtils.isEmpty(children);
    }

    public void addChild(CategoryNode node) {
        children.add(node);
    }
}
