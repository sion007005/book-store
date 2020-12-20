package sion.bookstore.domain.sample.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SampleRepositoryTest {
    @Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private DataSource dataSource;

    @Test
    public void testDataSource() throws Exception{
        //통신과 같이 열어주는 클래스 생성시 try()에 넣으면 자동으로 try catch 종료시 close한다.
        try(Connection conn = dataSource.getConnection()){
            System.out.println(conn);
        }catch(Exception e){e.printStackTrace();
        }
    }

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

        Long id = sampleRepository.insert(sample);

        Sample result = sampleRepository.findOne(id);

        System.out.println("result : " + id);
    }
}