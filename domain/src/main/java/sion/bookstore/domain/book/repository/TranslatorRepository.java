package sion.bookstore.domain.book.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TranslatorRepository {
    Long create(Translator translator);
    void update(Translator translator);
//    List<Translator> findAll(TranslatorSearchCondition translatorSearchCondition);
    List<Translator> findAllByBookId(Long bookId);
}
