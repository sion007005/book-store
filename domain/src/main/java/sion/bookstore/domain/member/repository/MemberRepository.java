package sion.bookstore.domain.member.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import sion.bookstore.domain.member.service.MemberSearchCondition;

import java.util.List;

@Mapper
@Repository
public interface MemberRepository {
    Long create(Member member);
    Member findOneById(Long id);
    Member findOneByEmail(String email);
    Long update(Member member);
    List<Member> findAll(MemberSearchCondition condition);
    long countAll(MemberSearchCondition condition);
}
