package sion.bookstore.admin.controller.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.admin.ResponseData;
import sion.bookstore.domain.category.service.CategoryService;

@RequiredArgsConstructor
@Controller
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/category/node")
    @ResponseBody
    public ResponseData getNode() {
        return ResponseData.success(categoryService.findCategoryNode());
    }

}
