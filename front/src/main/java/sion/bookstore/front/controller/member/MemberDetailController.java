package sion.bookstore.front.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.domain.auth.User;
import sion.bookstore.domain.auth.UserContext;
import sion.bookstore.domain.member.repository.Member;
import sion.bookstore.domain.member.service.MemberService;
import sion.bookstore.front.ResponseData;
import sion.bookstore.front.login.LoginRequired;

/**
 * 내 정보보기 페이지 데이터를 내려주는 컨트롤러
 */
@Controller
@RequiredArgsConstructor
public class MemberDetailController {
    private final MemberService memberService;

    @GetMapping("/my-info")
    @ResponseBody
    @LoginRequired
    public ResponseData getMyInfo() {
        User user = UserContext.get();
        Member member = memberService.findOneById(user.getMemberId());

        return ResponseData.success(member);
    }
}
