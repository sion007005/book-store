package sion.bookstore.domain.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.auth.UserContext;
import sion.bookstore.domain.book.repository.BookCategory;
import sion.bookstore.domain.book.repository.BookCategoryRepository;
import sion.bookstore.domain.category.repository.Category;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCategoryService {
    private final BookCategoryRepository bookCategoryRepository;

    public Long create(Long categoryId, Long bookId) {
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

    public BookCategory findOne(Long bookId) {
        return bookCategoryRepository.findMappingByBookId(bookId);
    }

    public List<Category> findCategoriesByBookId(Long bookId) {
        return bookCategoryRepository.findCategoriesByBookId(bookId);
    }

    public void updateNewMapping(Long bookId, Long categoryId) {
        delete(bookId);
        create(categoryId, bookId);
    }

    public void delete(Long bookId) {
        BookCategory mapping = bookCategoryRepository.findMappingByBookId(bookId);
        mapping.setModifiedAt(new Date());
        mapping.setModifiedBy(UserContext.get().getUserEmail());
        mapping.setDeleted(true);

        bookCategoryRepository.update(mapping);
    }

}
