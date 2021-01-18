package sion.bookstore.domain.book.thema.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import sion.bookstore.domain.book.thema.service.ThemaSectionSearchCondition;

import java.util.List;

@Mapper
@Repository
public interface ThemaSectionRepository {
    Long create(ThemaSection themaSection);
    ThemaSection findOne(Long themaSectionId);
    void update(ThemaSection themaSection);
    List<ThemaSection> findAll(ThemaSectionSearchCondition themaSectionSearchCondition);
    Long countAll(ThemaSectionSearchCondition themaSectionSearchCondition);
}
