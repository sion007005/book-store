package sion.bookstore.domain.sample.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SampleRepository {
    Sample findOne(Long id);
    List<Sample> findAll();
    Long insert(Sample sample);
}
