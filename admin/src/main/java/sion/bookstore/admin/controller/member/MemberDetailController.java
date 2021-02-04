package sion.bookstore.admin.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import sion.bookstore.admin.AdminOnly;
import sion.bookstore.domain.member.repository.Member;
import sion.bookstore.domain.member.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MemberDetailController {
    private MemberService memberService;

    @GetMapping("/member/{id}")
    @AdminOnly
    public ModelAndView getMemberDetail(@PathVariable Long id) {
        Member member = memberService.findOneById(id);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("member", member);

        return mav;
    }
}
