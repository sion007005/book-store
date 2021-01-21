package sion.bookstore.domain.book.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.book.repository.*;
import sion.bookstore.domain.parser.ParsedBook;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class KaKaoBookMaker {
    private final KakaoBookApiRepository kakaoBookApiRepository;
    private final ImageUploadService imageUploadService;

    public List<Book> requestBookList(List<ParsedBook> list) {
        List<Book> bookList = new ArrayList<>();

        for (ParsedBook parsedBook : list) {
            log.info("\t=>kakao api : {}", parsedBook.getImageUrl());
            KakaoBook.Document kakaoBookDocument = kakaoBookApiRepository.getKakaoResponse(parsedBook.getIsbn13());

            if (Objects.isNull(kakaoBookDocument)) {
                kakaoBookDocument = kakaoBookApiRepository.getKakaoResponse(parsedBook.getIsbn10());
            }

            if (Objects.isNull(kakaoBookDocument)) {
                continue;
            }

            Book book = makeBook(parsedBook, kakaoBookDocument);
            addAuthor(book, kakaoBookDocument);
            addTranslator(book, kakaoBookDocument);

            bookList.add(book);
        }

        return bookList;
    }

    public Book makeBook(ParsedBook parsedBook, KakaoBook.Document kakaoBookDocument) {
        Book book = new Book();
        book.setIsbn10(parsedBook.getIsbn10());
        book.setIsbn13(parsedBook.getIsbn13());
        book.setThumbnail(uploadImageFile(parsedBook));
        book.setCreatedAt(new Date());
        book.setCreatedBy("sion");
        book.setModifiedAt(new Date());
        book.setModifiedBy("sion");
        book.setDeleted(false);

        book.setTitle(kakaoBookDocument.getTitle());
        book.setContent(kakaoBookDocument.getContents());
//        book.setAuthors(kakaoBookDocument.getAuthors());
//        book.setTranslators(kakaoBookDocument.getTranslators());
        book.setPublishedAt(kakaoBookDocument.getDatetime());
        book.setPublisher(kakaoBookDocument.getPublisher());
        book.setPrice(kakaoBookDocument.getPrice());
        book.setSalePrice(kakaoBookDocument.getSale_price());
        book.setStatus(kakaoBookDocument.getStatus());

        return book;
    }

    private String uploadImageFile(ParsedBook book) {
        return imageUploadService.upload(book.getImageUrl(), book.getIsbn13(), "jpeg");
    }

    private void addAuthor(Book book, KakaoBook.Document kakaoBookDocument) {
        List<Author> authorList = new ArrayList<>();
        String[] authors = kakaoBookDocument.getAuthors();

        for (String name : authors) {
            Author author = new Author();
            author.setName(name);
            author.setBookId(book.getId());
            author.setCreatedAt(new Date());
            author.setCreatedBy("sion");
            author.setModifiedAt(new Date());
            author.setModifiedBy("sion");
            author.setDeleted(false);

            authorList.add(author);
        }

        book.setAuthors(authorList);
    }

    private void addTranslator(Book book, KakaoBook.Document kakaoBookDocument) {
        List<Translator> translatorList = new ArrayList<>();
        String[] translators = kakaoBookDocument.getTranslators();

        for (String name : translators) {
            Translator translator = new Translator();
            translator.setName(name);
            translator.setBookId(book.getId());
            translator.setCreatedAt(new Date());
            translator.setCreatedBy("sion");
            translator.setModifiedAt(new Date());
            translator.setModifiedBy("sion");
            translator.setDeleted(false);

            translatorList.add(translator);
        }

        book.setTranslators(translatorList);
    }
}
