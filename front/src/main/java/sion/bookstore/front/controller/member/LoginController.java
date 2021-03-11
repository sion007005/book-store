package sion.bookstore.front.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.domain.login.LoginService;
import sion.bookstore.domain.member.repository.Member;
import sion.bookstore.domain.utils.CookieUtils;
import sion.bookstore.front.FrontConstants;
import sion.bookstore.front.ResponseData;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService generalLoginService;
    private final Environment environment;

    @PostMapping("/login")
    @ResponseBody
    public ResponseData login(String email, String inputPassword, HttpServletResponse response) {
        Member authenticatedMember = generalLoginService.checkMemberAndPassword(email, inputPassword);

        Cookie cookie = getCookie(authenticatedMember);
        response.addCookie(cookie);

        return ResponseData.success("success");
    }

    private Cookie getCookie(Member member) {
        String encryptedCookieValue = generalLoginService.getEncryptedCookieValue(member);

        Cookie cookie = new Cookie(CookieUtils.makeCookieName(CookieUtils.findActiveProfile(), FrontConstants.COOKIE_KEY), encryptedCookieValue);
        cookie.setPath("/");
        cookie.setMaxAge(-1);

        return cookie;
    }
}
