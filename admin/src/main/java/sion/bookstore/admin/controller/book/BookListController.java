package sion.bookstore.admin.controller.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.admin.ResponseData;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.service.BookSearchCondition;
import sion.bookstore.domain.book.service.BookService;

@Controller
@RequiredArgsConstructor
public class BookListController {
    private final BookService bookService;

    @GetMapping("/book/list")
    @ResponseBody
    public ResponseData getList(BookSearchCondition searchCondition) {
        // TODO 파라미터 받을 때는 절대 map이 아닌 Model 객체

        Page<Book> pageBook = bookService.findAll(searchCondition);
        return ResponseData.success(pageBook);
//        throw new RuntimeException("test exception");
    }

}
