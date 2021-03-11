package sion.bookstore.domain.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j
public class CookieUtils {
    private CookieUtils() {}
    @Autowired
    private static Environment environment;

    public static String getValue(HttpServletRequest request, String name) {
        Cookie[] list = request.getCookies();

        if (list == null) {
            return null;
        }

        for (Cookie cookie : list) {
            if (cookie.getName().contains(findActiveProfile())) {
                return cookie.getValue();
            }
        }

        return null;
    }

    public static String makeCookieName(String profile, String baseName) {
        return profile + "_" + baseName;
    }

    public static String findActiveProfile() {
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