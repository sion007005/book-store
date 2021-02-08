package sion.bookstore.admin.controller.book;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sion.bookstore.admin.AdminOnly;
import sion.bookstore.admin.ResponseData;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.service.*;
import sion.bookstore.domain.category.repository.Category;
import sion.bookstore.domain.utils.FileUploadUtil;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final TranslatorService translatorService;
    private final BookValidator bookValidator;
    private final FileUploadUtil fileUploadUtil;
    private final BookCategoryService bookCategoryService;


    @Value("${book.image.path}")
    private String imagePath;

    @PostMapping("/book/create")
    @ResponseBody
    @AdminOnly
    public ResponseData create(Book book, Long categoryId) {
        bookValidator.validate(book, "book");
        book.setThumbnail(fileUploadUtil.uploadFile(book.getCoverImageFile(), imagePath));
        bookService.createAndCategoryMapping(categoryId, book);

        return ResponseData.success(book.getId());
    }

    @GetMapping("/book/{id}")
    public ModelAndView getBookDetail(@PathVariable Long id) {
        Book book = bookService.findOne(id);
        List<Category> categoryList = bookCategoryService.findCategoriesByBookId(id);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("book", book);
        mav.addObject("categoryList", categoryList);

        return mav;
    }

    @GetMapping("/book/list")
    @ResponseBody
    public ResponseData getList(BookSearchCondition searchCondition) {
        Page<Book> pageBook = bookService.findAll(searchCondition);
        return ResponseData.success(pageBook);
    }

    @PostMapping("/book/update")
    @ResponseBody
    @AdminOnly
    public ResponseData update(Book book, Long newCategoryId) {
        bookValidator.validate(book, "book");

        // update form view에서 기존의 이미지 thumbnail값을 hidden으로 보내줘야 함
        fileUploadUtil.deleteExistingFile(book.getThumbnail());
        book.setThumbnail(fileUploadUtil.uploadFile(book.getCoverImageFile(), imagePath));

        bookService.update(book);
        bookCategoryService.updateNewMapping(book.getId(), newCategoryId);
        authorService.updateNewMapping(book);
        translatorService.updateNewMapping(book);

        return ResponseData.success(book.getId());
    }
}
