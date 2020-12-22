package sion.bookstore.domain.sample.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sion.bookstore.domain.ApplicationConfiguration;

import java.util.Date;

//@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest (classes={ApplicationConfiguration.class, SampleRepository.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SampleRepositoryTest {
    @Autowired
    private SampleRepository sampleRepository;

    @Test
    public void insert() {
        Sample sample = new Sample();
        sample.setEmail("test@gmail.com");
        sample.setName("tester");
        sample.setDescription("desc");
        sample.setCreatedAt(new Date());
        sample.setCreatedBy("me");
        sample.setModifiedAt(new Date());
        sample.setModifiedBy("me");

        sampleRepository.insert(sample);

//        Sample result = sampleRepository.findOne(id);
        Sample expected = sampleRepository.findOne(sample.getId());

        System.out.println("result : " + expected.getId());
    }
}