package sion.bookstore.domain.book.thema.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sion.bookstore.domain.ApplicationConfiguration;
import sion.bookstore.domain.book.thema.service.ThemaSectionSearchCondition;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={ApplicationConfiguration.class, ThemaSectionRepository.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ThemaSectionRepositoryTest {
    @Autowired
    private ThemaSectionRepository themaSectionRepository;

    @Test
    public void createAndFindOneTest() {
        ThemaSection themaSection = new ThemaSection();
        themaSection.setOrderNo(3);
        themaSection.setType("화제의 책");
        themaSection.setTitle("화제의 책");
        themaSection.setDescription("20-30대가 가장 많이 산 책");
        themaSection.setCreatedAt(new Date());
        themaSection.setCreatedBy("sion");
        themaSection.setModifiedAt(new Date());
        themaSection.setModifiedBy("sion");
        themaSection.setDeleted(false);

        themaSectionRepository.create(themaSection);
        ThemaSection actual = themaSectionRepository.findOne(themaSection.getId());

        log.info("저장한 테마 이름 : {}", themaSection.getTitle());
        assertEquals(themaSection.getOrderNo(), actual.getOrderNo());
    }

    @Test
    public void findAllTest() {
        ThemaSectionSearchCondition condition = new ThemaSectionSearchCondition();
        List<ThemaSection> themaSectionList = themaSectionRepository.findAll(condition);

        for (ThemaSection themaSection : themaSectionList) {
            log.info("order number : {}", themaSection.getOrderNo());
            log.info("title : {}", themaSection.getTitle());
        }

        assertEquals(3, themaSectionList.size());
    }


}