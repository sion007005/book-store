package sion.bookstore.admin.controller.book.thema;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.admin.ResponseData;
import sion.bookstore.domain.book.thema.repository.ThemaSection;
import sion.bookstore.domain.book.thema.service.ThemaSectionSearchCondition;
import sion.bookstore.domain.book.thema.service.ThemaSectionService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ThemaSectionListController {
    private final ThemaSectionService themaSectionService;

    @GetMapping("/thema/list")
    @ResponseBody
    public ResponseData getList() {
        ThemaSectionSearchCondition condition = new ThemaSectionSearchCondition();
        List<ThemaSection> themaSectionList = themaSectionService.findAllWithBooks(condition);

        return ResponseData.success(themaSectionList);
    }
}
