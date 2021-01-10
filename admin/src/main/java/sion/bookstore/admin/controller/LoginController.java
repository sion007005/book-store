package sion.bookstore.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.admin.ResponseData;
import sion.bookstore.domain.login.LoginService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @ResponseBody
    public ResponseData login(String email, String password, HttpServletResponse response) {
        String encryptedSid = loginService.check(email, password);
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
