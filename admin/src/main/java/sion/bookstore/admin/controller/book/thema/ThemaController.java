package sion.bookstore.admin.controller.book.thema;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.admin.ResponseData;
import sion.bookstore.domain.book.thema.repository.ThemaSection;
import sion.bookstore.domain.book.thema.service.ThemaSectionSearchCondition;
import sion.bookstore.domain.book.thema.service.ThemaSectionService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ThemaController {
    private final ThemaSectionService themaSectionService;
    private final ThemaSectionValidator themaSectionValidator;

    @PostMapping("/thema/create")
    @ResponseBody
    public ResponseData createAndBookMapping(ThemaSection themaSection) {
//        themaSectionValidator.validate(themaSection, "thema section");
        themaSectionService.createAndBookMapping(themaSection);

        return ResponseData.success(themaSection.getId());
    }

    @PostMapping("/thema/delete/{id}")
    @ResponseBody
    public ResponseData delete(@PathVariable Long id) {
        themaSectionService.delete(id);
        return ResponseData.success("successfully removed one thema section");
    }

    @GetMapping("/thema/{id}")
    @ResponseBody
    public ResponseData getThemaSectionDetail(@PathVariable Long id) {
        ThemaSection themaSection = themaSectionService.findOneWithBooks(id);
        return ResponseData.success(themaSection);
    }

    @GetMapping("/thema/list")
    @ResponseBody
    public ResponseData getList() {
        ThemaSectionSearchCondition condition = new ThemaSectionSearchCondition();
        List<ThemaSection> themaSectionList = themaSectionService.findAllWithBooks(condition);

        return ResponseData.success(themaSectionList);
    }

    @PostMapping("/thema/update")
    @ResponseBody
    public ResponseData update(ThemaSection themaSection) {
        // TODO HasValueValidator 정규식 확인...
//        themaSectionValidator.validate(themaSection, "thema section");
        themaSectionService.update(themaSection);

        return ResponseData.success(themaSection.getId());
    }
}
