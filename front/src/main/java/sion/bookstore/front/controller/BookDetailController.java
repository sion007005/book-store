package sion.bookstore.front.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.service.BookCategoryService;
import sion.bookstore.domain.book.service.BookService;
import sion.bookstore.domain.category.repository.Category;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookDetailController {
    private final BookService bookService;
    private final BookCategoryService bookCategoryService;

    @GetMapping("/book/{id}")
    public ModelAndView getBookDetail(@PathVariable Long id) {
        Book book = bookService.findOneById(id);
        List<Category> categoryList = bookCategoryService.findCategoriesByBookId(id);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("book", book);
        mav.addObject("categoryList", categoryList);

        return mav;
    }
}
