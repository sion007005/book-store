package sion.bookstore.domain.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import sion.bookstore.domain.member.repository.Member;
import sion.bookstore.domain.member.repository.MemberRepository;

import java.util.List;

public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Integer create(Member member) {
        return memberRepository.create(member);
    }

    public Member findOne(Integer id) {
        return memberRepository.findOne(id);
    }

    public Page<Member> findPageByCondition(MemberSearchCondition condition) {
        List<Member> memberList = memberRepository.findAll(condition);
        long totalElements = memberRepository.countAll(condition);
        return new PageImpl<>(memberList, condition.getPageable(), totalElements);
    }
}
