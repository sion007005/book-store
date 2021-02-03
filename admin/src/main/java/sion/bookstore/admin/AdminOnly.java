package sion.bookstore.admin;

import java.lang.annotation.*;

/**
 * 관리자 권한이 필요한 요청을 구분하기 위해 사용한다.
 */
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdminOnly {
}
