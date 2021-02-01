package sion.bookstore.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(scanBasePackages={"sion.bookstore"})
@ServletComponentScan
public class FrontBookStoreApplication {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.default", "local");
        SpringApplication.run(FrontBookStoreApplication.class, args);
    }

}
