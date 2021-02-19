package sion.bookstore.domain.book.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.BaseAuditor;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.repository.KakaoBook;
import sion.bookstore.domain.book.repository.KakaoBookApiRepository;
import sion.bookstore.domain.parser.ParsedBook;

import java.util.ArrayList;
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
        book.setCoverImagePath(uploadImageFile(parsedBook));
        BaseAuditor.setCreationInfo(book);

        book.setTitle(kakaoBookDocument.getTitle());
        book.setContent(kakaoBookDocument.getContents());
//        book.setAuthors(kakaoBookDocument.getAuthors());
//        book.setTranslators(kakaoBookDocument.getTranslators());
        book.setPublishedAt(kakaoBookDocument.getDatetime());
        book.setPublisher(kakaoBookDocument.getPublisher());
        book.setPrice(kakaoBookDocument.getPrice());
        book.setSalePrice(kakaoBookDocument.getSale_price());
        book.setStatus(SaleStatus.IN_STOCK);

        return book;
    }

    private String uploadImageFile(ParsedBook book) {
        return imageUploadService.upload(book.getImageUrl(), book.getIsbn13(), "jpeg");
    }

    private void addAuthor(Book book, KakaoBook.Document kakaoBookDocument) {
//        List<Author> authorList = new ArrayList<>();
        String[] authors = kakaoBookDocument.getAuthors();

        if (Objects.nonNull(authors[0])) {
            book.setAuthor1(authors[0]);
        }

        if (Objects.nonNull(authors[1])) {
            book.setAuthor2(authors[1]);
        }

        if (Objects.nonNull(authors[2])) {
            book.setAuthor3(authors[2]);
        }
//
//        for (String name : authors) {
//            Author author = new Author();
//            author.setName(name);
//            author.setBookId(book.getId());
//            author.setCreatedAt(new Date());
//            author.setCreatedBy("sion");
//            author.setModifiedAt(new Date());
//            author.setModifiedBy("sion");
//            author.setDeleted(false);
//
//            authorList.add(author);
//        }

//        book.setAuthors(authorList);
    }

    private void addTranslator(Book book, KakaoBook.Document kakaoBookDocument) {
//        List<Translator> translatorList = new ArrayList<>();
        String[] translators = kakaoBookDocument.getTranslators();

        if (Objects.nonNull(translators[0])) {
            book.setTranslator1(translators[0]);
        }

        if (Objects.nonNull(translators[1])) {
            book.setTranslator2(translators[1]);
        }

        if (Objects.nonNull(translators[2])) {
            book.setTranslator3(translators[2]);
        }

//        for (String name : translators) {
//            Translator translator = new Translator();
//            translator.setName(name);
//            translator.setBookId(book.getId());
//            translator.setCreatedAt(new Date());
//            translator.setCreatedBy("sion");
//            translator.setModifiedAt(new Date());
//            translator.setModifiedBy("sion");
//            translator.setDeleted(false);
//
//            translatorList.add(translator);
//        }
//
//        book.setTranslators(translatorList);
    }
}
