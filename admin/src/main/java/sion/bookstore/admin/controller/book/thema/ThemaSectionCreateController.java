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
public class ThemaSectionCreateController {
    private final ThemaSectionService themaSectionService;
    private final ThemaSectionValidator themaSectionValidator;

    @PostMapping("/thema/create")
    @ResponseBody
    public ResponseData createAndBookMapping(ThemaSection themaSection) {
//        themaSectionValidator.validate(themaSection, "thema section");
        themaSectionService.createAndBookMapping(themaSection);

        return ResponseData.success(themaSection.getId());
    }
}
