package sion.bookstore.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import sion.bookstore.domain.login.LoginService;
import sion.bookstore.domain.member.service.MemberService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private LoginService loginService;

    public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
        String email = (String)request.getParameter("email");
        String plainPassword = request.getParameter("password");
        String returnUrl = request.getParameter("returnUrl");

        ModelAndView mav = loginService.check(email, plainPassword);
        Object loginSuccess = mav.getModel().get("login");

        if (loginSuccess.equals("success")) {
            String encryptedSid = (String) mav.getModel().get("encryptedSid");
            response.addCookie(getCookie(encryptedSid));
            mav.addObject("returnUrl", returnUrl);
        }

        return mav;
    }

    private Cookie getCookie(String encryptedSid) {
        Cookie cookie = new Cookie("sid", encryptedSid);
        cookie.setPath("/");
        cookie.setMaxAge(-1);

        return cookie;
    }

}
