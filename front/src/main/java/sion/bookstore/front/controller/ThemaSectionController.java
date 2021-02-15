package sion.bookstore.front.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.domain.book.theme.repository.ThemeSection;
import sion.bookstore.domain.book.theme.service.ThemeSectionSearchCondition;
import sion.bookstore.domain.book.theme.service.ThemeSectionService;
import sion.bookstore.front.ResponseData;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ThemaSectionController {
    private final ThemeSectionService themeSectionService;

    @GetMapping("/thema/list")
    @ResponseBody
    public ResponseData findAll() {
        ThemeSectionSearchCondition condition = new ThemeSectionSearchCondition();
        List<ThemeSection> themeSectionList = themeSectionService.findAllWithBooks(condition);

        return ResponseData.success(themeSectionList);
    }

    @GetMapping("/thema/{id}")
    @ResponseBody
    public ResponseData findOneById(@PathVariable Long id) {
        ThemeSection themeSection = themeSectionService.findOneWithBooks(id);
        return ResponseData.success(themeSection);
    }
}
