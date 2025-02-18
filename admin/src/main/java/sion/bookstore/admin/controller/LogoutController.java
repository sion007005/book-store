package sion.bookstore.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import sion.bookstore.admin.AdminConstants;
import sion.bookstore.admin.AdminUser;
import sion.bookstore.domain.auth.UserContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class LogoutController {

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("sid", "");
        cookie.setPath("/");

        UserContext.remove();
        UserContext.set(AdminUser.unauthenticatedUser(request.getLocalAddr()));
        response.addCookie(cookie);

        String returnUrl= request.getParameter("returnUrl");
        if (Objects.isNull(returnUrl)) {
            returnUrl = AdminConstants.REDIRECT_MAIN_PAGE;
        }

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("returnUrl", returnUrl);
        return mav;
    }
}
