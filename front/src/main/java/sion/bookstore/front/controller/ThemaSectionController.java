package sion.bookstore.front.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.domain.book.thema.repository.ThemaSection;
import sion.bookstore.domain.book.thema.service.ThemaSectionSearchCondition;
import sion.bookstore.domain.book.thema.service.ThemaSectionService;
import sion.bookstore.front.ResponseData;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ThemaSectionController {
    private final ThemaSectionService themaSectionService;

    @GetMapping("/thema/list")
    @ResponseBody
    public ResponseData getList() {
        ThemaSectionSearchCondition condition = new ThemaSectionSearchCondition();
        List<ThemaSection> themaSectionList = themaSectionService.findAllWithBooks(condition);

        return ResponseData.success(themaSectionList);
    }

    @GetMapping("/thema/{id}")
    @ResponseBody
    public ResponseData getOneThemaSection(@PathVariable Long id) {
        ThemaSection themaSection = themaSectionService.findOneWithBooks(id);
        return ResponseData.success(themaSection);
    }
}
