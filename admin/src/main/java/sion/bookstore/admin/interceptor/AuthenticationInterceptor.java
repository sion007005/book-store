package sion.bookstore.admin.interceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import sion.bookstore.admin.AdminConstants;
import sion.bookstore.admin.AdminOnly;
import sion.bookstore.admin.AdminUser;
import sion.bookstore.domain.auth.User;
import sion.bookstore.domain.auth.UserContext;
import sion.bookstore.domain.login.AuthenticationException;
import sion.bookstore.domain.login.LoginService;
import sion.bookstore.domain.member.repository.Member;
import sion.bookstore.domain.member.service.MemberService;
import sion.bookstore.domain.utils.AES256Util;
import sion.bookstore.domain.utils.CookieUtils;
import sion.bookstore.domain.utils.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    private final MemberService memberService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        userSetting(request);
        loginCheck(handler);

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                            @Nullable ModelAndView modelAndView) throws Exception {
    }

    private void userSetting(HttpServletRequest request) {
        String encryptedValue = CookieUtils.getValue(request, AdminConstants.COOKIE_KEY);

        if (Objects.isNull(encryptedValue) || encryptedValue.equals("")) {
            log.info("Unauthenticated User Setting");
            UserContext.set(AdminUser.unauthenticatedUser(request.getRemoteAddr()));
            return;
        }

        try {
            AES256Util encryptUtil = new AES256Util();
            String decryptedValue = encryptUtil.decrypt(encryptedValue);
            Long memberId = getMemberId(decryptedValue);

            Member member = memberService.findOneById(memberId);
            User user = AdminUser.authenticatedUser(member, request.getRemoteAddr());
            UserContext.set(user); // threadLocal에 인증된 admin user 저장
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            throw new AuthenticationException(e.getMessage(), e);
        }
    }

    private Long getMemberId(String decryptedValue) {
        String[] splitValues = decryptedValue.split(LoginService.COOKIE_VALUE_SEPERATOR);
        Long memberId = NumberUtils.parseLong(splitValues[1]);

        return memberId;
    }

    private void loginCheck(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            User user = UserContext.get();

            if(hm.hasMethodAnnotation(AdminOnly.class) && !user.authenticated()) {
                throw new AuthenticationException("관리자만 접근 가능한 페이지입니다.");
            }
        }
    }
}
