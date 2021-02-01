package sion.bookstore.admin.controller.book.thema;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.admin.ResponseData;
import sion.bookstore.domain.book.thema.service.ThemaSectionService;

@Controller
@RequiredArgsConstructor
public class ThemaSectionDeleteController {
    private final ThemaSectionService themaSectionService;

    @PostMapping("/thema/delete/{id}")
    @ResponseBody
    public ResponseData delete(@PathVariable Long id) {
        themaSectionService.delete(id);
        return ResponseData.success("successfully removed one thema section");
    }
}
