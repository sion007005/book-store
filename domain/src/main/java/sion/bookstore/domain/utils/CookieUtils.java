package sion.bookstore.domain.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Slf4j
public class CookieUtils {
    private CookieUtils() {
    }

    public static String getValue(HttpServletRequest request, String name) {
        Cookie[] list = request.getCookies();

        if (list == null) {
            return null;
        }

        for (Cookie cookie : list) {
            if (cookie.getName().contains(name)) {
                return cookie.getValue();
            }
        }

        return null;
    }

    public static String makeCookieName(String profile, String baseName) {
        return profile + "_" + baseName;
    }
}