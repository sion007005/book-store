package sion.bookstore.admin.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import sion.bookstore.admin.AdminOnly;
import sion.bookstore.domain.member.repository.Member;
import sion.bookstore.domain.member.service.MemberSearchCondition;
import sion.bookstore.domain.member.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MemberListController {
    private final MemberService memberService;

    @GetMapping("/member/list")
    @AdminOnly
    public ModelAndView getMemberList(MemberSearchCondition condition) {
        Page<Member> memberPage = memberService.findAll(condition);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("memberList", memberPage);

        return mav;
    }
}
