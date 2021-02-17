package sion.bookstore.front.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import sion.bookstore.domain.auth.UserContext;
import sion.bookstore.front.login.GeneralUser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class LogoutController {

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("sid", "");
        cookie.setPath("/");

        UserContext.remove();
        UserContext.set(GeneralUser.unauthenticatedUser(request.getLocalAddr()));
        response.addCookie(cookie);

        //TODO
        String returnUrl = "localhost:9090/main";
        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("returnUrl", returnUrl);
        return mav;
    }
}
