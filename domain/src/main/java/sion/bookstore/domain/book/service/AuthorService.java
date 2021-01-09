package sion.bookstore.domain.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.book.repository.Author;
import sion.bookstore.domain.book.repository.AuthorRepository;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Long create(Author author) {
        return authorRepository.create(author);
    }

    public void update(Author author) {
        authorRepository.update(author);
    }

}
