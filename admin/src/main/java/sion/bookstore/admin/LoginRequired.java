package sion.bookstore.admin;

import java.lang.annotation.*;

/**
 * 로그인이 필요한 요청을 구분하기 위해 사용
 */
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginRequired {
}
