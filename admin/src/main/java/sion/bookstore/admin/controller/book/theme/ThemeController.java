package sion.bookstore.admin.controller.book.theme;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sion.bookstore.domain.book.theme.repository.ThemeSection;
import sion.bookstore.domain.book.theme.service.ThemeSectionSearchCondition;
import sion.bookstore.domain.book.theme.service.ThemeSectionService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ThemeController {
    private final ThemeSectionService themeSectionService;
    private final ThemeSectionValidator themeSectionValidator;

    @PostMapping("/theme/create")
    @ResponseBody
    public ModelAndView createAndBookMapping(ThemeSection themeSection) {
        themeSectionValidator.validate(themeSection, "theme section");
        themeSectionService.createAndBookMapping(themeSection);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("themeSectionId", themeSection.getId());
        return mav;
    }

    @PostMapping("/theme/delete/{id}")
    @ResponseBody
    public ModelAndView delete(@PathVariable Long id) {
        themeSectionService.delete(id);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("themeSectionId", id);

        return mav;
    }

    @GetMapping("/theme/{id}")
    @ResponseBody
    public ModelAndView getThemeSectionDetail(@PathVariable Long id) {
        ThemeSection themeSection = themeSectionService.findOneWithBooks(id);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("themeSection", themeSection);

        return mav;
    }

    @GetMapping("/theme/list")
    @ResponseBody
    public ModelAndView getList() {
        ThemeSectionSearchCondition condition = new ThemeSectionSearchCondition();
        List<ThemeSection> themeSectionList = themeSectionService.findAllWithBooks(condition);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("themeSectionList", themeSectionList);

        return mav;
    }

    @PostMapping("/theme/update")
    @ResponseBody
    public ModelAndView update(ThemeSection themeSection) {
        themeSectionValidator.validate(themeSection, "theme section");
        themeSectionService.update(themeSection);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("themeSectionId", themeSection.getId());

        return mav;
    }
}
