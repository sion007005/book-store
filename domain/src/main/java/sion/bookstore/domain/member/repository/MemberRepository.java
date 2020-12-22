package sion.bookstore.domain.member.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface MemberRepository {
    int create(Member member);
    Optional<Member> findByEmail(String email);
    Optional<Member> findById(Long id);
    List<Member> findAll();
}
