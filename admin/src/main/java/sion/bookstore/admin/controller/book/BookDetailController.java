package sion.bookstore.admin.controller.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.admin.ResponseData;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.service.BookService;

@Controller
@RequiredArgsConstructor
public class BookDetailController {
    private final BookService bookService;

    @GetMapping("/book/{id}")
    @ResponseBody
    public ResponseData getBookDetail(@PathVariable Long id) {
        Book book = bookService.findOne(id);
        return ResponseData.success(book);
    }
}
