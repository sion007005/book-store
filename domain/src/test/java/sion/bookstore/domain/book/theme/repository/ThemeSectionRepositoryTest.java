package sion.bookstore.domain.book.theme.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sion.bookstore.domain.ApplicationConfiguration;
import sion.bookstore.domain.book.theme.service.ThemeSectionSearchCondition;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={ApplicationConfiguration.class, ThemeSectionRepository.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ThemeSectionRepositoryTest {
    @Autowired
    private ThemeSectionRepository themeSectionRepository;

    @Test
    public void createAndFindOneTest() {
        ThemeSection themeSection = new ThemeSection();
        themeSection.setOrderNo(3);
        themeSection.setType("화제의 책");
        themeSection.setTitle("화제의 책");
        themeSection.setDescription("20-30대가 가장 많이 산 책");
        themeSection.setCreatedAt(new Date());
        themeSection.setCreatedBy("sion");
        themeSection.setModifiedAt(new Date());
        themeSection.setModifiedBy("sion");
        themeSection.setDeleted(false);

        themeSectionRepository.create(themeSection);
        ThemeSection actual = themeSectionRepository.findOne(themeSection.getId());

        log.info("저장한 테마 이름 : {}", themeSection.getTitle());
        assertEquals(themeSection.getOrderNo(), actual.getOrderNo());
    }

    @Test
    public void findAllTest() {
        ThemeSectionSearchCondition condition = new ThemeSectionSearchCondition();
        List<ThemeSection> themeSectionList = themeSectionRepository.findAll(condition);

        for (ThemeSection themeSection : themeSectionList) {
            log.info("order number : {}", themeSection.getOrderNo());
            log.info("title : {}", themeSection.getTitle());
        }

        assertEquals(3, themeSectionList.size());
    }


}