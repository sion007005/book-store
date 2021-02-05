package sion.bookstore.admin.controller.book;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.admin.AdminOnly;
import sion.bookstore.admin.ResponseData;
import sion.bookstore.domain.utils.FileUploadUtil;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.service.BookService;

@Controller
@RequiredArgsConstructor
public class BookCreateController {
    private final BookService bookService;
    private final BookValidator bookValidator;
    private final FileUploadUtil fileUploadUtil;

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
}
