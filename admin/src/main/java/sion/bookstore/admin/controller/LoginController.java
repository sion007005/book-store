package sion.bookstore.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.admin.AdminConstants;
import sion.bookstore.admin.ResponseData;
import sion.bookstore.domain.login.LoginService;
import sion.bookstore.domain.member.repository.Member;
import sion.bookstore.domain.utils.CookieUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService adminLoginService;
    private final Environment environment;

    @PostMapping("/login")
    @ResponseBody
    public ResponseData login(String email, String inputPassword, HttpServletResponse response) {
        Member authenticatedMember = adminLoginService.checkMemberAndPassword(email, inputPassword);

        Cookie cookie = getCookie(authenticatedMember);
        response.addCookie(cookie);

        return ResponseData.success("success");
    }

    private Cookie getCookie(Member member) {
        String encryptedCookieValue = adminLoginService.getEncryptedCookieValue(member);

        Cookie cookie = new Cookie(CookieUtils.makeCookieName(findActiveProfile(), AdminConstants.COOKIE_KEY), encryptedCookieValue);
        cookie.setPath("/");
        cookie.setMaxAge(-1);

        return cookie;
    }


    private String findActiveProfile() {
        if(Arrays.stream(environment.getActiveProfiles()).anyMatch(
                env -> (env.equalsIgnoreCase("local")))) {
            return "local";
        }

        if(Arrays.stream(environment.getActiveProfiles()).anyMatch(
                env -> (env.equalsIgnoreCase("dev")) )) {
            return "dev";
        }

        return "";
    }
}
