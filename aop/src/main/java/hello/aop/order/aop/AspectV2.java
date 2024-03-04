package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Around와 @Pointcut을 분리해서 사용 가능하다.
 * 장점 : advice가 하나 이상이고 같은 pointcut을 적용할 때 중복 코드 없이 사용 가능하다.
 * 즉, 하나의 포인트컷 표현식을 여러 어드바이스에서 함께 사용할 수 있다.
 */
@Slf4j
@Aspect
public class AspectV2 {

    @Pointcut("execution(* hello.aop.order..*(..))") //hello.aop.order 패키지와 하위 패키지
    private void allOrder() {
    } //pointcut signature

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); //join point 시그니처
        return joinPoint.proceed();
    }

}
