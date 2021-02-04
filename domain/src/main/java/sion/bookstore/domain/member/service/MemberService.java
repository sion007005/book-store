package sion.bookstore.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.member.repository.Member;
import sion.bookstore.domain.member.repository.MemberRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long create(Member member) {
        member.setCreatedAt(new Date());
        // TODO 멤버 가입시 createdBy & modifiedBy는 누구로? (admin User / general User)
        member.setCreatedBy(member.getEmail());
        member.setModifiedAt(new Date());
        member.setModifiedBy(member.getEmail());
        member.setDeleted(false);

        return memberRepository.create(member);
    }

    public Long update(Member member) {
        member.setModifiedAt(new Date());
        member.setModifiedBy(member.getEmail());
        member.setDeleted(false);

        return memberRepository.update(member);
    }

    public Member findOneById(Long id) {
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

    public Page<Member> findAll(MemberSearchCondition condition) {
        List<Member> memberList = memberRepository.findAll(condition);
        Long totalCount = memberRepository.countAll(condition);
        Page<Member> memberPage = new PageImpl<>(memberList, condition.getPageable(), totalCount);

        return memberPage;
    }
}
