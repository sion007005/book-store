package sion.bookstore.admin.controller.book;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.admin.ResponseData;
import sion.bookstore.domain.utils.FileUploadUtil;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.service.BookService;

@Controller
@RequiredArgsConstructor
public class BookUpdateController {
    private final BookService bookService;
    private final BookValidator bookValidator;
    private  final FileUploadUtil fileUploadUtil;
    @Value("${book.image.path}")
    private String imagePath;

    @PostMapping("/book/{id}")
    @ResponseBody
    public ResponseData update(Book book) {
        bookValidator.validate(book, "book");
        // TODO update form view에서 기존의 이미지 thumbnail값을 hidden으로 보내줘야 함
        fileUploadUtil.deleteExistingFile(book.getThumbnail());
        book.setThumbnail(fileUploadUtil.uploadFile(book.getCoverImageFile(), imagePath));
        bookService.update(book);

        return ResponseData.success(book.getId());
    }

}
