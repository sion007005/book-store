package sion.bookstore.front.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.domain.category.service.CategoryService;
import sion.bookstore.front.ResponseData;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/category/node")
    @ResponseBody
    public ResponseData findAllCategoryNode() {
        return ResponseData.success(categoryService.findAllCategoryNode());
    }
}
