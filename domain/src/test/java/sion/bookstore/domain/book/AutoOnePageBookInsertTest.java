package sion.bookstore.domain.book;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;
import sion.bookstore.domain.ApplicationConfiguration;
import sion.bookstore.domain.book.repository.*;
import sion.bookstore.domain.book.service.BookRegisterFacade;
import sion.bookstore.domain.parser.ParsedBook;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={ApplicationConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@WebAppConfiguration
public class AutoOnePageBookInsertTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TranslatorRepository translatorRepository;

    @Autowired
    private BookRegisterFacade bookRegisterFacade;


    @Test
    @Transactional
    public void test() throws IOException {
        StopWatch stopWatch = new StopWatch("book_parse");
        stopWatch.start();
        Long categoryId = 5L;
        String url = "http://www.yes24.com/24/Category/Display/001001022005";
        bookRegisterFacade.register(url, categoryId);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }

    private void createAuthor(Book book) {
        List<Author> authors = book.getAuthors();

        for (Author author : authors) {
            author.setBookId(book.getId());
            author.setName(author.getName());
            author.setCreatedAt(new Date());
            author.setCreatedBy("sion");
            author.setModifiedAt(new Date());
            author.setModifiedBy("sion");
            author.setDeleted(false);

            authorRepository.create(author);
        }
    }

    private void createTranslator(Book book) {
        List<Translator> translators = book.getTranslators();

        if (translators.size() == 0) {
            return;
        }

        for (Translator translator : translators) {
            translator.setBookId(book.getId());
            translator.setName(translator.getName());
            translator.setCreatedAt(new Date());
            translator.setCreatedBy("sion");
            translator.setModifiedAt(new Date());
            translator.setModifiedBy("sion");
            translator.setDeleted(false);

            translatorRepository.create(translator);
        }
    }

    private String uploadImageFile(ParsedBook book) throws IOException {
        String imageUrl = book.getImageUrl();
        String fileType = "jpeg";
        String fileName = book.getIsbn13() != null ? book.getIsbn13() : book.getIsbn10();
        String thumbnailPath = "D:/book-store/thumbnail/"  + book.getIsbn13() + "." + fileType;

        try {
            URL url = new URL(imageUrl);
            BufferedImage image = ImageIO.read(url);
            File file = new File(thumbnailPath);

            ImageIO.write(image, fileType, file);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return thumbnailPath;
    }
}
