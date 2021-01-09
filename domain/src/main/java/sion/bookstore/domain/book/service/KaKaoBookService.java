package sion.bookstore.domain.book.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.repository.KakaoBook;

@Slf4j
@Service
public class KaKaoBookService {
    @Autowired
    // http 요청을 날리는 데 사용
    private RestTemplate externalRestTemplate;

    public Book requestBookInfo(String isbn) {
        String url = "https://dapi.kakao.com/v3/search/book?target=isbn&query=" + isbn;

        //Set the headers you need send
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK 67a350f0b04f7b78c3b5b9cc37d0eda6");

        //Create a new HttpEntity
        final HttpEntity<String> entity = new HttpEntity<>(headers);

        //Execute the method writing your HttpEntity to the request
        ResponseEntity<KakaoBook> response = externalRestTemplate.exchange(url, HttpMethod.GET, entity, KakaoBook.class);

        if (response.getBody().getDocuments().size() != 0) {
            Book book = new Book();
            book.setTitle(response.getBody().getDocuments().get(0).getTitle());
            book.setContent(response.getBody().getDocuments().get(0).getContents());
            book.setAuthors(response.getBody().getDocuments().get(0).getAuthors());
            book.setAuthors(response.getBody().getDocuments().get(0).getTranslators());
            book.setPublishedAt(response.getBody().getDocuments().get(0).getDatetime());
            book.setPublisher(response.getBody().getDocuments().get(0).getPublisher());
            book.setPrice(response.getBody().getDocuments().get(0).getPrice());
            book.setSalePrice(response.getBody().getDocuments().get(0).getSale_price());
//            book.setThumbnail(response.getBody().getDocuments().get(0).getThumbnail());
            book.setStatus(response.getBody().getDocuments().get(0).getStatus());

            return book;
        }

        return null;
    }
}
