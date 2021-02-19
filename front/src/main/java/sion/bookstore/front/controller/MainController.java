package sion.bookstore.front.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import sion.bookstore.domain.book.theme.repository.ThemeSection;
import sion.bookstore.domain.book.theme.service.ThemeSectionSearchCondition;
import sion.bookstore.domain.book.theme.service.ThemeSectionService;
import sion.bookstore.domain.category.service.CategoryNode;
import sion.bookstore.domain.category.service.CategoryService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ThemeSectionService themeSectionService;
    private final CategoryService categoryService;

    @GetMapping({"/", "/index", "/main"})
    public ModelAndView getMainPage() {
        CategoryNode categoryNode = categoryService.findAllCategoryNodes();

        ThemeSectionSearchCondition condition = new ThemeSectionSearchCondition();
        List<ThemeSection> themeSectionList = themeSectionService.findAllWithBooks(condition);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("themeSectionList", themeSectionList);
        mav.addObject("categoryNode", categoryNode);

        return mav;
    }
}
