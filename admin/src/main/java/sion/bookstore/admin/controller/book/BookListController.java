package sion.bookstore.admin.controller.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.admin.ResponseData;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.service.BookSearchCondition;
import sion.bookstore.domain.book.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class BookListController {
    private final BookService bookService;

    @GetMapping("/book/list")
    @ResponseBody
    public ResponseData getList(
//            @PageableDefault(size=5, sort="id") Pageable pageable,
            @RequestParam HashMap<String, String> searchCondition) {
        BookSearchCondition condition = setSearchCondition(searchCondition);
//        if (pageable.getPageSize() != 5 || pageable.getPageNumber() != 1) {
//            condition.setSize(pageable.getPageSize());
//            condition.setPage(pageable.getPageNumber());
//        }

        Page<Book> bookList = bookService.findAll(condition);
        return ResponseData.success(bookList);
    }

    public BookSearchCondition setSearchCondition(HashMap condition) {
        // TODO 이렇게 하는건... OCP에 어긋남
        BookSearchCondition bookSearchCondition = new BookSearchCondition();

        // 검색정보
        if (Objects.nonNull(condition.get("title"))) {
            bookSearchCondition.setTitle((String) condition.get("title"));
        }

        if (Objects.nonNull(condition.get("categoryId"))) {
            bookSearchCondition.setCategoryId((Long) (condition.get("categoryId")));
        }

        if (Objects.nonNull(condition.get("name"))) {
            bookSearchCondition.setName((String) (condition.get("name")));
        }

        if (Objects.nonNull(condition.get("isbn"))) {
            bookSearchCondition.setIsbn((String) (condition.get("isbn")));
        }

        // 정렬 정보
        if (Objects.nonNull(condition.get("orderType"))) {
            bookSearchCondition.setOrderType((String) (condition.get("orderType")));
        }

        // 페이지 정보
        if (Objects.nonNull(condition.get("page"))) {
            bookSearchCondition.setPage(Integer.parseInt((String) (condition.get("page"))));
        }

        if (Objects.nonNull(condition.get("size"))) {
            bookSearchCondition.setSize(Integer.parseInt((String) (condition.get("size"))));
        }


        return bookSearchCondition;
    }

}
