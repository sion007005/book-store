package sion.bookstore.front.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.domain.auth.User;
import sion.bookstore.domain.auth.UserContext;
import sion.bookstore.domain.member.repository.Member;
import sion.bookstore.domain.member.service.MemberService;
import sion.bookstore.domain.utils.FileUploadUtil;
import sion.bookstore.front.ResponseData;
import sion.bookstore.front.login.LoginRequired;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final FileUploadUtil fileUploadUtil;
    private final MemberValidator memberValidator;

    @Value("${profile.image.path}")
    private String imagePath;

    @PostMapping("/member/register")
    @ResponseBody
    public ResponseData register(Member member) {
        memberValidator.validate(member, "member");
        memberService.register(member);

        return ResponseData.success(member.getId());
    }

    @LoginRequired
    @PostMapping("/member/update")
    @ResponseBody
    public ResponseData update(Member member) {
        memberValidator.validate(member, "member");
        memberService.update(member);

        return ResponseData.success(member.getId());
    }

    @GetMapping("/my-info")
    @ResponseBody
    @LoginRequired
    public ResponseData getMyInfo() {
        User user = UserContext.get();
        Member member = memberService.findOneById(user.getMemberId());

        return ResponseData.success(member);
    }



}
