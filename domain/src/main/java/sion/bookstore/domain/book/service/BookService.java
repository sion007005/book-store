package sion.bookstore.domain.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.book.repository.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final TranslatorService translatorService;
    private final BookCategoryService bookCategoryService;

    public Long create(Book book) {
        return bookRepository.create(book);
    }

    public Book findOne(Long bookId) {
        return bookRepository.findOne(bookId);
    }

    public void update(Book book) {
        bookRepository.update(book);
    }

    public List<Book> findCategoryBooks(BookCategorySearchCondition condition) {
//  추후 생성 할 컨트롤러에서 아래 서치 정보Bo를 담아서 서비스로 넘겨야 한다.
//        BookCategorySearchCondition condition = new BookCategorySearchCondition();
//        condition.setCategoryId(찾을 카테고리 아이디 번호);
//        condition.setPage(현재 페이지);
        List<Book> bookList = new ArrayList<>();
        List<BookCategory> bookCategoryList = bookCategoryService.findBooksByCategoryId(condition);

        for (BookCategory bookCategory : bookCategoryList) {
            Long bookId = bookCategory.getBookId();
            Book book = bookRepository.findOne(bookId);
            bookList.add(book);
        }

        return bookList;
    }

    public void createAll(Long categoryId, List<Book> bookList) {
        for (Book book : bookList) {
            create(book);
            createAuthors(book);
            translatorService.create(book);
            bookCategoryService.create(categoryId, book.getId());
        }
    }

    private void createAuthors(Book book) {
        List<String> authors = Arrays.asList(book.getAuthors());

        for (String name : authors) {
            Author author = new Author();
            author.setBookId(book.getId());
            author.setName(name);
            author.setCreatedAt(new Date());
            author.setCreatedBy("sion");
            author.setModifiedAt(new Date());
            author.setModifiedBy("sion");
            author.setDeleted(false);

            authorRepository.create(author);
        }
    }
}
