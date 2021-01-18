package sion.bookstore.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.member.repository.Member;
import sion.bookstore.domain.member.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Integer create(Member member) {
        return memberRepository.create(member);
    }

    public Member findOneById(Integer id) {
        return memberRepository.findOneById(id);
    }

    public Member findOneByEmail(String email) {
        return memberRepository.findOneByEmail(email);
    }

    public Page<Member> findPageByCondition(MemberSearchCondition condition) {
        List<Member> memberList = memberRepository.findAll(condition);
        long totalElements = memberRepository.countAll(condition);
        return new PageImpl<>(memberList, condition.getPageable(), totalElements);
    }
}
