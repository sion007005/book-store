package sion.bookstore.domain.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.book.repository.Author;
import sion.bookstore.domain.book.repository.AuthorRepository;
import sion.bookstore.domain.book.repository.Book;

import java.util.Arrays;
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
