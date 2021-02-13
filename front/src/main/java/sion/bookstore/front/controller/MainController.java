package sion.bookstore.front.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import sion.bookstore.domain.book.thema.repository.ThemaSection;
import sion.bookstore.domain.book.thema.service.ThemaSectionSearchCondition;
import sion.bookstore.domain.book.thema.service.ThemaSectionService;
import sion.bookstore.domain.category.service.CategoryNode;
import sion.bookstore.domain.category.service.CategoryService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ThemaSectionService themaSectionService;
    private final CategoryService categoryService;

    @GetMapping({"/", "/index", "/main"})
    public ModelAndView getMainPage() {
        CategoryNode categoryNode = categoryService.findAllCategoryNode();

        ThemaSectionSearchCondition condition = new ThemaSectionSearchCondition();
        List<ThemaSection> themaSectionList = themaSectionService.findAllWithBooks(condition);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("themaSectionList", themaSectionList);
        mav.addObject("categoryNode", categoryNode);

        return mav;
    }
}
