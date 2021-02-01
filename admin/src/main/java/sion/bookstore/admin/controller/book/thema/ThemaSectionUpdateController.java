package sion.bookstore.admin.controller.book.thema;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.admin.ResponseData;
import sion.bookstore.domain.book.thema.repository.ThemaSection;
import sion.bookstore.domain.book.thema.service.ThemaSectionService;

@Controller
@RequiredArgsConstructor
public class ThemaSectionUpdateController {
    private final ThemaSectionService themaSectionService;
    private final ThemaSectionValidator themaSectionValidator;

    @PostMapping("/thema/update")
    @ResponseBody
    public ResponseData update(ThemaSection themaSection) {
        // TODO HasValueValidator 정규식 확인...
//        themaSectionValidator.validate(themaSection, "thema section");
        themaSectionService.update(themaSection);

        return ResponseData.success(themaSection.getId());
    }
}
