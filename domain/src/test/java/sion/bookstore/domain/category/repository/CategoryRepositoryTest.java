package sion.bookstore.domain.category.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sion.bookstore.domain.ApplicationConfiguration;
import sion.bookstore.domain.category.service.CategorySearchCondition;
import sion.bookstore.domain.utils.RandomOut;

import java.util.List;

import static org.junit.Assert.assertEquals;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={ApplicationConfiguration.class, CategoryRepository.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findOne() {
        Category expected = CategoryMock.getCategory("test", 1L, 1);
        categoryRepository.create(expected);
        Long id = expected.getId();

        Category actual = categoryRepository.findOne(id);

        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    public void update() {
        String name = "test";

        Category category = CategoryMock.getCategory("updateTest", 1L, 1);
        categoryRepository.create(category);

        Category expected = categoryRepository.findOne(category.getId());
        expected.setName(name);
        categoryRepository.update(expected);

        Category actual = categoryRepository.findOne(expected.getId());

        assertEquals(expected, actual);
    }

    @Test
    public void findAll() {
        String keyword = RandomOut.getRandomStr(10);

        categoryRepository.create(CategoryMock.getCategory("1" + keyword + "1", 1L, 1));
        categoryRepository.create(CategoryMock.getCategory("2" + keyword + "2", 1L, 1));
        categoryRepository.create(CategoryMock.getCategory("3" + keyword + "3", 1L,1));

        CategorySearchCondition condition = new CategorySearchCondition();
        condition.setKeyword(keyword);
        List<Category> actual = categoryRepository.findAll(condition);

        assertEquals(3, actual.size());
    }

    @Test
    public void findAll_KeywordIsEmpty() {
        String keyword = RandomOut.getRandomStr(10);
        int expected = 3;

        categoryRepository.create(CategoryMock.getCategory("1" + keyword + "1", 1L, 1));
        categoryRepository.create(CategoryMock.getCategory("2" + keyword + "2", 1L, 1));
        categoryRepository.create(CategoryMock.getCategory("3" + keyword + "3", 1L, 1));
        categoryRepository.create(CategoryMock.getCategory("4" + keyword + "4", 1L, 1));
        categoryRepository.create(CategoryMock.getCategory("5" + keyword + "5", 1L, 1));

        CategorySearchCondition condition = new CategorySearchCondition();
        condition.setSize(expected);

        condition.setKeyword(null);
        List<Category> actual = categoryRepository.findAll(condition);

        assertEquals(expected, actual.size());
    }

    @Test
    public void countAll() {
        String keyword = RandomOut.getRandomStr(10);
        int expected = 5;

        categoryRepository.create(CategoryMock.getCategory("1" + keyword + "1", 1L, 1));
        categoryRepository.create(CategoryMock.getCategory("2" + keyword + "2", 1L, 1));
        categoryRepository.create(CategoryMock.getCategory("3" + keyword + "3", 1L, 1));
        categoryRepository.create(CategoryMock.getCategory("4" + keyword + "4", 1L, 1));
        categoryRepository.create(CategoryMock.getCategory("5" + keyword + "5", 1L, 1));

        CategorySearchCondition condition = new CategorySearchCondition();
        condition.setSize(3);

        condition.setKeyword(keyword);
        long actual = categoryRepository.countAll(condition);

        assertEquals(expected, actual);
    }

    @Test
    public void findAllByCategoryLevel() {
        List<Category> categoryList = categoryRepository.findAllByCategoryLevel(3);
        categoryList.forEach(category -> {
            log.info("category level name: {} {}", category.getLevel(), category.getName());
        });
    }

    @Test
    public void findAllById() {
        Long limitId = 264L;
        List<Category> categoryList = categoryRepository.findAllById(limitId);

        categoryList.forEach(category -> {
            log.info("category level id name: {} {}, {}", category.getLevel(), category.getId(), category.getName());
        });
    }
}