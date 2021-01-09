package sion.bookstore.domain.book;

import sion.bookstore.domain.book.repository.Book;

import java.util.Date;

public class BookMock {
   public static Book getBook(String title, String isbn, String isbn13) {
        Book book = new Book();
        book.setTitle(title);
        book.setContent("test content here..");
        book.setIsbn10(isbn);
        book.setIsbn13(isbn13);
        book.setPublishedAt(new Date());
        book.setPublisher("좋은출판사");
        book.setPrice(13000);
        book.setSalePrice(12000);
        book.setThumbnail("/imgage/img");
        book.setStatus("판매");
        book.setCreatedAt(new Date());
        book.setCreatedBy("시온");
        book.setModifiedAt(new Date());
        book.setModifiedBy("시온");
        book.setDeleted(false);

       return book;
   }

}
