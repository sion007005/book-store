package sion.bookstore.domain.book.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import sion.bookstore.domain.ApplicationConfiguration;
import sion.bookstore.domain.book.migration.BookMigrationService;
import sion.bookstore.domain.book.migration.MigrationRunnable;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={ApplicationConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@WebAppConfiguration
public class BookMigrationServiceTest {
    @Autowired
    BookMigrationService bookMigrationService;

    @Test
    public void test() {
//        for (int i = 1; i <= 4; i++) {
            Runnable r = new MigrationRunnable(1, 600, bookMigrationService);
            Thread thread = new Thread(r);
            thread.start();
//        }
    }
}
