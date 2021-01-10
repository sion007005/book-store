package sion.bookstore.domain.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.book.repository.Author;
import sion.bookstore.domain.book.repository.AuthorRepository;
import sion.bookstore.domain.book.repository.Book;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

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

            create(author);
        }
    }

    public void update(Author author) {
        authorRepository.update(author);
    }


}
