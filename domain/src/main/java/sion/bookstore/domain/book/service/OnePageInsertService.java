package sion.bookstore.domain.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.parser.OnePageParser;
import sion.bookstore.domain.parser.ParsedBook;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class OnePageInsertService {
    @Autowired
    private OnePageParser onePageParser;

    @Autowired
    private KaKaoBookService kaKaoBookService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookCategoryService bookCategoryService;

    public void parseAndcreate(String url, Long categoryId) throws IOException {
        List<ParsedBook> parsedBookList = onePageParser.parse(url);

        for (ParsedBook parsedBook : parsedBookList) {
            if (Objects.isNull(parsedBook)) continue;

            Book book = kaKaoBookService.requestBookInfo(parsedBook.getIsbn13());

            if (Objects.isNull(book)) {
                book = kaKaoBookService.requestBookInfo(parsedBook.getIsbn10());
            }

            if (Objects.isNull(book)) {
                //TODO kakao api에서 아무정보도 오지 않으면, yes24 정보로 넣어주기
                continue;
            }

            book.setIsbn10(parsedBook.getIsbn10());
            book.setIsbn13(parsedBook.getIsbn13());
            book.setThumbnail(uploadImageFile(parsedBook));
            book.setCreatedAt(new Date());
            book.setCreatedBy("sion");
            book.setModifiedAt(new Date());
            book.setModifiedBy("sion");
            book.setDeleted(false);

            bookService.create(book);
            bookCategoryService.create(categoryId, book.getId());
        }

    }

    private String uploadImageFile(ParsedBook book) throws IOException {
        String imageUrl = book.getImageUrl();
        String fileType = "jpeg";
        String thumbnailPath = "D:/book-store/thumbnail/"  + book.getIsbn13() + "." + fileType;

        try {
            URL url = new URL(imageUrl);
            BufferedImage image = ImageIO.read(url);
            File file = new File(thumbnailPath);

            ImageIO.write(image, fileType, file);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return thumbnailPath;
    }
}
