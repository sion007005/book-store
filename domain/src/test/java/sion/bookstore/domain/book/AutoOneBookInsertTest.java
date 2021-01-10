//package sion.bookstore.domain.book;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import sion.bookstore.domain.ApplicationConfiguration;
//import sion.bookstore.domain.book.repository.Book;
//import sion.bookstore.domain.book.service.BookCategoryService;
//import sion.bookstore.domain.book.service.BookService;
//import sion.bookstore.domain.book.service.KaKaoBookMaker;
//import sion.bookstore.domain.parser.OneBookParser;
//import sion.bookstore.domain.parser.ParsedBook;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.net.URL;
//import java.util.Date;
//import java.util.Objects;
//
//@Slf4j
//@RunWith(SpringRunner.class)
//@WebAppConfiguration
//@SpringBootTest(classes={ApplicationConfiguration.class})
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class AutoOneBookInsertTest {
//    @Autowired
//    private OneBookParser oneBookParser;
//
//    @Autowired
//    private KaKaoBookMaker kaKaoBookMaker;
//
////    @Autowired
//    private BookService bookService;
//
//    @Autowired
//    private BookCategoryService bookCategoryService;
//
//    @Test
//    public void test() throws IOException {
////        String oneBookUrl = "http://www.yes24.com/Product/Goods/64611488";
////        String oneBookUrl = "http://www.yes24.com/Product/Goods/3664564";
//        String oneBookUrl = "http://www.yes24.com/Product/Goods/90114956";
//        Long categoryId = 270L;
//
//        ParsedBook parsedBook = oneBookParser.parse(oneBookUrl);
//
//        if (Objects.isNull(parsedBook)) return;
//
////        Book book = kaKaoBookMaker.setKaKaoInfo(parsedBook.getIsbn13());
////
////        if (Objects.isNull(book)) {
////            book = kaKaoBookMaker.setKaKaoInfo(parsedBook.getIsbn10());
////        }
//
//        if (Objects.isNull(book)) {
//            //TODO kakao api에서 아무정보도 오지 않으면, yes24 정보로 넣어주기
//            return;
//        }
//
//        book.setIsbn10(parsedBook.getIsbn10());
//        book.setIsbn13(parsedBook.getIsbn13());
//        book.setThumbnail(uploadImageFile(parsedBook));
//        book.setCreatedAt(new Date());
//        book.setCreatedBy("sion");
//        book.setModifiedAt(new Date());
//        book.setModifiedBy("sion");
//        book.setDeleted(false);
//
//
//        bookService.create(book);
//        bookCategoryService.create(categoryId, book.getId());
//    }
//
//    /**
//     *
//     * @param book
//     * @throws IOException
//     * book.getThumbnail()에 저장된 url 이미지를 서버폴더에 저장하고,
//     * 그 경로를 book.setThumbnail에 덮어쓰기
//     */
//    private String uploadImageFile(ParsedBook book) throws IOException {
//        String imageUrl = book.getImageUrl();
//        String fileType = "jpeg";
//        String thumbnailPath = "D:/book-store/thumbnail/"  + book.getIsbn13() + "." + fileType;
//
//        try {
//            URL url = new URL(imageUrl);
//            BufferedImage image = ImageIO.read(url);
//            File file = new File(thumbnailPath);
//
//            ImageIO.write(image, fileType, file);
//
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage(), e);
//        }
//
//        return thumbnailPath;
//    }
//}
