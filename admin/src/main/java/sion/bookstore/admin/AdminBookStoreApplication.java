package sion.bookstore.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(scanBasePackages={"sion.bookstore"})
@ServletComponentScan
public class AdminBookStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminBookStoreApplication.class, args);
    }

}
