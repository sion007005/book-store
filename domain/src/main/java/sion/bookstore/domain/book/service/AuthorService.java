package sion.bookstore.domain.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    public List<Author> findAllByName(AuthorSearchCondition condition) {
        return authorRepository.findAllByName(condition);
    }
}
