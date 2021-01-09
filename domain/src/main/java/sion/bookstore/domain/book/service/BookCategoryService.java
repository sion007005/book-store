package sion.bookstore.domain.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.book.repository.BookCategory;
import sion.bookstore.domain.book.repository.BookCategoryRepository;

import java.util.Date;

@Service
public class BookCategoryService {
    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    public Long create(long categoryId, long bookId) {
        BookCategory mapping = new BookCategory();
        mapping.setCategoryId(categoryId);
        mapping.setBookId(bookId);
        mapping.setCreatedAt(new Date());
        mapping.setCreatedBy("sion");
        mapping.setModifiedAt(new Date());
        mapping.setModifiedBy("sion");
        mapping.setDeleted(false);

        return bookCategoryRepository.create(mapping);
    }
}
