package sion.bookstore.domain.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final TranslatorService translatorService;
    private final BookCategoryService bookCategoryService;

    public Long create(Book book) {
        return bookRepository.create(book);
    }

    public void createAll(Long categoryId, List<Book> bookList) {
        for (Book book : bookList) {
            create(book);
            authorService.create(book);
            translatorService.create(book);
            bookCategoryService.create(categoryId, book.getId());
        }
    }

    public Book findOne(Long bookId) {
        return bookRepository.findOne(bookId);
    }

    //TODO
    // 1. [DONE] for문을 없애고 join 쿼리를 통해 가져온다.
    // 2. [DONE] 책 이름, 최근 출판일, 낮은 가격 순으로 정렬하는 기능 추가하기
    // 3. Page<Book>으로 페이징 지원하기(select count(*) 쿼리를 날려야 함)
    @Transactional(readOnly = true)
    public Page<Book> findAll(BookSearchCondition condition) {
//  추후 생성 할 컨트롤러에서 아래 서치 정보를 담아서 서비스로 넘겨야 한다.
//        BookCategorySearchCondition condition = new BookCategorySearchCondition();
//        condition.setCategoryId(찾을 카테고리 아이디 번호);
//        condition.setPage(현재 페이지);

        List<Book> bookList = bookRepository.findAll(condition);
        Long totalCount = bookRepository.countAll(condition);
        Page<Book> bookPage = new PageImpl<>(bookList, condition.getPageable(), totalCount);

        return bookPage;
    }

    public void update(Book book) {
        bookRepository.update(book);
    }
}
