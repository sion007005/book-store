package sion.bookstore.domain.book;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import sion.bookstore.domain.ApplicationConfiguration;

import java.util.Map;

/**
 * curl -X GET "https://dapi.kakao.com/v3/search/book?target=isbn&query=9791130631134" -H "Authorization: KakaoAK 67a350f0b04f7b78c3b5b9cc37d0eda6"
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={ApplicationConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class KakaoBookApiServiceTest {
    @Autowired
    // http 요청을 날리는 데 사용
    private RestTemplate externalRestTemplate;

    @Test
    public void test() {
        log.info("restTemplate : {}", externalRestTemplate);
        String url = "https://dapi.kakao.com/v3/search/book?target=isbn&query=9791130631134";

        //Set the headers you need send
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK 67a350f0b04f7b78c3b5b9cc37d0eda6");

        //Create a new HttpEntity
        final HttpEntity<String> entity = new HttpEntity<>(headers);

        //Execute the method writing your HttpEntity to the request
        ResponseEntity<Map> response = externalRestTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        log.info("response : {}", response.getBody());
    }

}
