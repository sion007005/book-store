package sion.bookstore.front.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.domain.login.LoginService;
import sion.bookstore.domain.utils.CookieUtils;
import sion.bookstore.front.AdminConstants;
import sion.bookstore.front.ResponseData;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService generalLoginService;
    private final Environment environment;

    @PostMapping("/login")
    @ResponseBody
    public ResponseData login(String email, String inputPassword, HttpServletResponse response) {
        String encryptedSid = generalLoginService.checkLoginMember(email, inputPassword);

        Cookie cookie = getCookie(encryptedSid);
        response.addCookie(cookie);

        return ResponseData.success("success");
    }

    private Cookie getCookie(String encryptedSid) {
        Cookie cookie = new Cookie(CookieUtils.makeCookieName(findActiveProfile(), AdminConstants.COOKIE_SID), encryptedSid);
        cookie.setPath("/");
        cookie.setMaxAge(-1);

        return cookie;
    }

    private String findActiveProfile() {
        if(Arrays.stream(environment.getActiveProfiles()).anyMatch(
                env -> (env.equalsIgnoreCase("local")))) {
            return "local";
        } else if(Arrays.stream(environment.getActiveProfiles()).anyMatch(
                env -> (env.equalsIgnoreCase("prod")) )) {
            return "prod";
        } else if(Arrays.stream(environment.getActiveProfiles()).anyMatch(
                env -> (env.equalsIgnoreCase("dev")) )) {
            return "dev";
        }

        return "local";
    }
}
