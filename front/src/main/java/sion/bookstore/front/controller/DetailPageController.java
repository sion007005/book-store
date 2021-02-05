package sion.bookstore.front.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import sion.bookstore.domain.book.service.BookService;
import sion.bookstore.domain.category.service.CategoryService;

@Controller
@RequiredArgsConstructor
public class DetailPageController {
    private final BookService bookService;
    private final CategoryService categoryService;

//    @GetMapping("/detail/{id}")

}
