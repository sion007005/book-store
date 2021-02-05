package sion.bookstore.domain.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.auth.UserContext;
import sion.bookstore.domain.book.repository.Author;
import sion.bookstore.domain.book.repository.AuthorRepository;
import sion.bookstore.domain.book.repository.Book;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Long create(Author author) {
        return authorRepository.create(author);
    }

    public void create(Book book) {
        List<Author> authors = book.getAuthors();
        for (Author author : authors) {
            author.setBookId(book.getId());
            author.setName(author.getName());
            author.setCreatedAt(new Date());
            author.setCreatedBy("sion");
            author.setModifiedAt(new Date());
            author.setModifiedBy("sion");
            author.setDeleted(false);

            authorRepository.create(author);
        }
    }

    // TODO CHECK 책 정보 수정할 때 작가 정보는 수정을 할 수도 있고 안 할 수도 있는데, modifiedAt을 어떻게 처리하지?
    // 책 정보를 등록할 때 작가도 함께 등록하므로
    // createdAt과 createdBy를 hidden으로 넘겨주지 않고
    // book의 createdAt과 createdBy를 따라간다.
    public void update(Book book) {
        List<Author> authors = book.getAuthors();
        for (Author author : authors) {
            author.setBookId(book.getId());
            author.setCreatedAt(book.getCreatedAt());
            author.setCreatedBy(book.getCreatedBy());
            author.setModifiedAt(new Date());
            author.setModifiedBy(UserContext.get().getUserEmail());
            author.setDeleted(false);

            authorRepository.update(author);
        }
    }

    public List<Author> findAllByBookId(Long bookId) {
        return authorRepository.findAllByBookId(bookId);
    }

    public List<Author> findAllByName(AuthorSearchCondition condition) {
        return authorRepository.findAllByName(condition);
    }
}
