package sion.bookstore.domain.member.repository;

import java.util.Date;

public class MemberMock {
    public static Member getMember(String name) {
        Member member = new Member();
        member.setName(name);
        member.setEmail("test0808@naver.com");
        member.setPassword("1234");
        member.setPasswordSalt("abc");
        member.setPhone("01011111111");
        member.setProfileImgPath("/img/img");
        member.setCreatedAt(new Date());
        member.setCreatedBy("test");
        member.setModifiedAt(new Date());
        member.setModifiedBy("test");
        member.setDeleted(false);
        return member;
    }
}
