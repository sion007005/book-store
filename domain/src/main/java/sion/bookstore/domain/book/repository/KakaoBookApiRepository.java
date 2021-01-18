package sion.bookstore.domain.book.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class KakaoBookApiRepository {
    // http 요청을 날리는 데 사용
    private final RestTemplate externalRestTemplate;
    
    public KakaoBook.Document getKakaoResponse(String isbn) {
        String url = "https://dapi.kakao.com/v3/search/book?target=isbn&query=" + isbn;

        //Set the headers you need send
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK 67a350f0b04f7b78c3b5b9cc37d0eda6");

        //Create a new HttpEntity
        final HttpEntity<String> entity = new HttpEntity<>(headers);

        //Execute the method writing your HttpEntity to the request
        ResponseEntity<KakaoBook> response = externalRestTemplate.exchange(url, HttpMethod.GET, entity, KakaoBook.class);
        if (!hasDocuments(response)) {
            return null;
        }
        return response.getBody().getDocuments().get(0);
    }

    private boolean hasDocuments(ResponseEntity<KakaoBook> response) {
        return response.getBody().getDocuments().size() != 0;
    }
}
