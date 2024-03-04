package hello.aop.member.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME) // 런타임때 까지 해당 애노테이션이 살아 있음. -> 실행 시 동적으로 적용 가능
public @interface ClassAop {
}
