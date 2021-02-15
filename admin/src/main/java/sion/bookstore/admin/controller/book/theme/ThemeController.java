package sion.bookstore.admin.controller.book.theme;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.admin.ResponseData;
import sion.bookstore.domain.book.theme.repository.ThemeSection;
import sion.bookstore.domain.book.theme.service.ThemeSectionSearchCondition;
import sion.bookstore.domain.book.theme.service.ThemeSectionService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ThemeController {
    private final ThemeSectionService themeSectionService;
    private final ThemeSectionValidator themeSectionValidator;

    @PostMapping("/thema/create")
    @ResponseBody
    public ResponseData createAndBookMapping(ThemeSection themeSection) {
//        themaSectionValidator.validate(themaSection, "thema section");
        themeSectionService.createAndBookMapping(themeSection);

        return ResponseData.success(themeSection.getId());
    }

    @PostMapping("/thema/delete/{id}")
    @ResponseBody
    public ResponseData delete(@PathVariable Long id) {
        themeSectionService.delete(id);
        return ResponseData.success("successfully removed one thema section");
    }

    @GetMapping("/thema/{id}")
    @ResponseBody
    public ResponseData getThemaSectionDetail(@PathVariable Long id) {
        ThemeSection themeSection = themeSectionService.findOneWithBooks(id);
        return ResponseData.success(themeSection);
    }

    @GetMapping("/thema/list")
    @ResponseBody
    public ResponseData getList() {
        ThemeSectionSearchCondition condition = new ThemeSectionSearchCondition();
        List<ThemeSection> themeSectionList = themeSectionService.findAllWithBooks(condition);

        return ResponseData.success(themeSectionList);
    }

    @PostMapping("/thema/update")
    @ResponseBody
    public ResponseData update(ThemeSection themeSection) {
        // TODO HasValueValidator 정규식 확인...
//        themaSectionValidator.validate(themaSection, "thema section");
        themeSectionService.update(themeSection);

        return ResponseData.success(themeSection.getId());
    }
}
