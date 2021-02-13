package sion.bookstore.domain.member.repository;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sion.bookstore.domain.ApplicationConfiguration;

import static org.junit.Assert.assertEquals;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={ApplicationConfiguration.class, MemberRepository.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void findOneById() {
        Member expected = MemberMock.getMember("test", "test1@naver.com");
        memberRepository.register(expected);
        Long id = expected.getId();

        Member actual = memberRepository.findOneById(id);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    public void findOneByEmail() {
        Member expected = MemberMock.getMember("test2", "test2@naver.com");
        memberRepository.register(expected);
        String email = expected.getEmail();

        Member actual = memberRepository.findOneByEmail(email);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getEmail(), actual.getEmail());
    }

    @Test
    public void update() {
        String name = "updateTest";

        Member member = MemberMock.getMember("test", "updateTest@naver.com");
        memberRepository.register(member);

        Member expected = memberRepository.findOneById(member.getId());
        expected.setName(name);
        memberRepository.update(expected);

        Member actual = memberRepository.findOneById(expected.getId());

        assertEquals(expected, actual);
    }

    @Test
    public void findAll() {
//        String keyword = RandomOut.getRandomStr(10);
//
//        memberRepository.create(MemberMock.getMember("1" + keyword + "1"));
//        memberRepository.create(MemberMock.getMember("2" + keyword + "2"));
//        memberRepository.create(MemberMock.getMember("3" + keyword + "3"));
//
//        MemberSearchCondition condition = new MemberSearchCondition();
//        condition.setKeyword(keyword);
//        List<Member> actual = memberRepository.findAll(condition);
//
//        assertEquals(3, actual.size());
    }

    @Test
    public void findAll_KeywordIsEmpty() {
//        String keyword = RandomOut.getRandomStr(10);
//        int expected = 3;
//
//        memberRepository.create(MemberMock.getMember("1" + keyword + "1"));
//        memberRepository.create(MemberMock.getMember("2" + keyword + "2"));
//        memberRepository.create(MemberMock.getMember("3" + keyword + "3"));
//        memberRepository.create(MemberMock.getMember("4" + keyword + "4"));
//        memberRepository.create(MemberMock.getMember("5" + keyword + "5"));
//
//        MemberSearchCondition condition = new MemberSearchCondition();
//        condition.setSize(expected);
//
//        condition.setKeyword(null);
//        List<Member> actual = memberRepository.findAll(condition);
//
//        assertEquals(expected, actual.size());
    }

    @Test
    public void countAll() {
//        String keyword = RandomOut.getRandomStr(10);
//        int expected = 5;
//
//        memberRepository.create(MemberMock.getMember("1" + keyword + "1"));
//        memberRepository.create(MemberMock.getMember("2" + keyword + "2"));
//        memberRepository.create(MemberMock.getMember("3" + keyword + "3"));
//        memberRepository.create(MemberMock.getMember("4" + keyword + "4"));
//        memberRepository.create(MemberMock.getMember("5" + keyword + "5"));
//
//        MemberSearchCondition condition = new MemberSearchCondition();
//        condition.setSize(3);
//
//        condition.setKeyword(keyword);
//        long actual = memberRepository.countAll(condition);
//
//        assertEquals(expected, actual);
    }
}
