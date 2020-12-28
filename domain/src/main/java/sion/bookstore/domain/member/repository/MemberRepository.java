package sion.bookstore.domain.member.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import sion.bookstore.domain.member.service.MemberSearchCondition;

import java.util.List;

@Mapper
@Repository
public interface MemberRepository {
    // 하나씩 만들어 나가기
    // null을 받을 수 있으려면, int가 아닌 Integer가 좀 더 적절하다.
    Integer create(Member member);

    Member findOne(Integer id);
    void update(Member member);
    List<Member> findAll(MemberSearchCondition condition);
    long countAll(MemberSearchCondition condition);
//    Optional<Member> findByEmail(String email);
//    Optional<Member> findById(Long id);
}
