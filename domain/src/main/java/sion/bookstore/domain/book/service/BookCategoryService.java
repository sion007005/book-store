package sion.bookstore.domain.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.book.repository.BookCategory;
import sion.bookstore.domain.book.repository.BookCategoryRepository;
import sion.bookstore.domain.category.repository.Category;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCategoryService {
    private final BookCategoryRepository bookCategoryRepository;

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

    public List<Category> findCategoriesByBookId(Long bookId) {
        return bookCategoryRepository.findCategoriesByBookId(bookId);
    }

}
