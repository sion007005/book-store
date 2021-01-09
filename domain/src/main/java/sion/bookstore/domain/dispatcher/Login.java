package sion.bookstore.domain.dispatcher;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD}) //이 annotation은 runtime시에 method에만 활용할 수 있다는 것을 의미함
public @interface Login {
}
