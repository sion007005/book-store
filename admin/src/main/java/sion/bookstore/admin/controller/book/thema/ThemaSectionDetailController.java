package sion.bookstore.admin.controller.book.thema;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.admin.ResponseData;
import sion.bookstore.domain.book.thema.repository.ThemaSection;
import sion.bookstore.domain.book.thema.service.ThemaSectionService;

@Controller
@RequiredArgsConstructor
public class ThemaSectionDetailController {
    private final ThemaSectionService themaSectionService;

    @GetMapping("/thema/{id}")
    @ResponseBody
    public ResponseData getThemaSectionDetail(@PathVariable Long id) {
        ThemaSection themaSection = themaSectionService.findOneWithBooks(id);
        return ResponseData.success(themaSection);
    }
}
