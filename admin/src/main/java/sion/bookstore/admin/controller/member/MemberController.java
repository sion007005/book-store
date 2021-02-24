package sion.bookstore.admin.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sion.bookstore.admin.AdminOnly;
import sion.bookstore.domain.member.repository.Member;
import sion.bookstore.domain.member.service.MemberSearchCondition;
import sion.bookstore.domain.member.service.MemberService;
import sion.bookstore.domain.utils.FileUploadUtil;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final FileUploadUtil fileUploadUtil;

    @Value("${profile.image.path}")
    private String imagePath;

    @PostMapping("/member/register")
    @ResponseBody
    public ModelAndView register(Member member) {
        member.setAdmin(true);
        Long memberId = memberService.register(member);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("memberId", memberId);
        return mav;
    }

    @GetMapping("/member/{id}")
    @AdminOnly
    public ModelAndView findOneById(@PathVariable Long id) {
        Member member = memberService.findOneById(id);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("member", member);

        return mav;
    }

    @GetMapping("/member/list")
    @AdminOnly
    public ModelAndView findAll(MemberSearchCondition condition) {
        // CHECK 관리자를 포함한 모든 회원을 불러옴
        Page<Member> memberPage = memberService.findAll(condition);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("memberPage", memberPage);

        return mav;
    }
}
