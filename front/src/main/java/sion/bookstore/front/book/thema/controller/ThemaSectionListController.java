package sion.bookstore.front.book.thema.controller;

import lombok.RequiredArgsConstructor;
import org.apache.http.protocol.ResponseDate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.domain.book.thema.repository.ThemaSection;
import sion.bookstore.domain.book.thema.repository.ThemaSectionRepository;
import sion.bookstore.domain.book.thema.service.ThemaSectionSearchCondition;
import sion.bookstore.front.ResponseData;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ThemaSectionListController {
    private final ThemaSectionRepository themaSectionRepository;

    @RequestMapping("/thema/list")
    @ResponseBody
    public ResponseData getList() {
        ThemaSectionSearchCondition condition = new ThemaSectionSearchCondition();
        List<ThemaSection> themaSectionList = themaSectionRepository.findAll(condition);

        return ResponseData.success(themaSectionList);
    }
}
