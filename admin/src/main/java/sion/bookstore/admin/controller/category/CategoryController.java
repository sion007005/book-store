package sion.bookstore.admin.controller.category;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import sion.bookstore.domain.category.repository.Category;
import sion.bookstore.domain.category.service.CategoryNode;
import sion.bookstore.domain.category.service.CategorySearchCondition;
import sion.bookstore.domain.category.service.CategoryService;

@RequiredArgsConstructor
@Controller
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/category/create")
    public ModelAndView create(Category category) {
        categoryService.create(category);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("categoryId", category.getId());

        return mav;
    }

    @PostMapping("/category/update")
    public ModelAndView update(Category category) {
        categoryService.update(category);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("categoryId", category.getId());

        return mav;
    }
    
    @GetMapping("/category/node")
    public ModelAndView findAllCategoryNodes() {
        CategoryNode allCategoryNode = categoryService.findAllCategoryNodes();

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("allCategoryNode", allCategoryNode);

        return mav;
    }

    @GetMapping("/category/list")
    public ModelAndView findAllCategory() {
        CategorySearchCondition condition = new CategorySearchCondition();
        condition.setSize(1000);
        Page<Category> categories = categoryService.findAllCategories(condition);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("categories", categories);

        return mav;
    }

}
