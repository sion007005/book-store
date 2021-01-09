package sion.bookstore.domain.book;

import sion.bookstore.domain.book.repository.Author;

import java.util.Date;

public class AuthorMock {
    public static Author getAuthor(Long bookId, String name) {
        Author author = new Author();
        author.setBookId(bookId);
        author.setName(name);
        author.setCreatedAt(new Date());
        author.setCreatedBy("시온");
        author.setModifiedAt(new Date());
        author.setModifiedBy("시온");
        author.setDeleted(false);

        return author;
    }
}
