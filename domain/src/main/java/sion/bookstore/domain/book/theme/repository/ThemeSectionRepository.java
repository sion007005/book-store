package sion.bookstore.domain.book.theme.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import sion.bookstore.domain.book.theme.service.ThemeSectionSearchCondition;

import java.util.List;

@Mapper
@Repository
public interface ThemeSectionRepository {
    Long create(ThemeSection themeSection);
    ThemeSection findOne(Long themeSectionId);
    void update(ThemeSection themeSection);
    List<ThemeSection> findAll(ThemeSectionSearchCondition themeSectionSearchCondition);
    Long countAll(ThemeSectionSearchCondition themeSectionSearchCondition);
}
