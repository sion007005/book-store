package sion.bookstore.front.interceptor;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import sion.bookstore.domain.auth.User;
import sion.bookstore.domain.auth.UserContext;
import sion.bookstore.domain.login.AuthenticationException;
import sion.bookstore.domain.member.repository.Member;
import sion.bookstore.domain.member.service.MemberService;
import sion.bookstore.domain.utils.AES256Util;
import sion.bookstore.domain.utils.CookieUtils;
import sion.bookstore.domain.utils.NumberUtils;
import sion.bookstore.front.login.GeneralUser;
import sion.bookstore.front.login.LoginRequired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private final MemberService memberService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        userSetting(request);
        loginCheck(handler);

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        log.info("ModelAndView: {}", modelAndView);
        // 모든 요청에 대해 _USER 키로 user를 담아준다.

        if (Objects.nonNull(modelAndView)) {
            modelAndView.addObject("_USER", UserContext.get());
        }
    }

    private void userSetting(HttpServletRequest request) {
        String encryptedSid = CookieUtils.getValue(request, "sid");

        if (Objects.isNull(encryptedSid) || encryptedSid.equals("")) {
            log.info("Unauthenticated User Setting");
            User user = GeneralUser.unauthenticatedUser(request.getRemoteAddr());
            UserContext.set(user);
            return;
        }

        try {
            AES256Util encryptUtil = new AES256Util();
            String decryptedSid = encryptUtil.decrypt(encryptedSid);
            Long memberId = NumberUtils.parseLong(decryptedSid);

            Member member = memberService.findOneById(memberId);
            User user = GeneralUser.authenticatedUser(memberId, member.getEmail(), member.getName(), request.getRemoteAddr());
            UserContext.set(user); // threadLocal에 인증된 general user 저장
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            throw new AuthenticationException(e.getMessage(), e);
        }
    }

    private void loginCheck(Object handler) {
        //로그인 체크 -> 필요한가(=@LoginRequired 어노테이션이 있는가)?
        // 1) 필요하다면,
        //   1-1) 로그인이 되었는가? 확인 후
        //   1-1) 로그인이 안 된 상태면, (프론트 단에서) 로그인 페이지로 redirection

        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            User user = UserContext.get();

            if(hm.hasMethodAnnotation(LoginRequired.class) && !user.authenticated()) {
                throw new AuthenticationException("로그인 후 이용 가능한 페이지입니다.");
            }
        }
    }

}
