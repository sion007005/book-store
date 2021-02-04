package sion.bookstore.front.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.domain.login.LoginService;
import sion.bookstore.domain.member.repository.Member;
import sion.bookstore.front.ResponseData;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService generalLoginService;

    @PostMapping("/login")
    @ResponseBody
    public ResponseData login(String email, String password, HttpServletResponse response) {
        Member member = generalLoginService.findExistingMember(email);
        generalLoginService.comparePassword(member, password);
        String encryptedSid = generalLoginService.getEncryptedSid(member.getId());

        Cookie cookie = getCookie(encryptedSid);
        response.addCookie(cookie);

        return ResponseData.success("success");
    }

    private Cookie getCookie(String encryptedSid) {
        Cookie cookie = new Cookie("sid", encryptedSid);
        cookie.setPath("/");
        cookie.setMaxAge(-1);

        return cookie;
    }
}
