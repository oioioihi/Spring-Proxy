package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Advice의 종류
 *
 * @Around : 메서드 호출 전후에 수행 된다. 가장 강력한 advice로 join point 실행 여부 선택, 반환 값 변환, 예외 변환 등이 가능하다.
 * @Before : join point 실행 이전에 실행 된다. ProceddingJoinPoint.proceed()를 사용하지 않고, 메서드 종료 시 자동으로 다음 타켓이 호출된다.
 * @AfterReturning : join point가 정상 완료 후 실행 된다.
 * @AfterThrowing : 메서드가 예외를 던지는 경우에 실행 된다.
 * @After : join point가 정상 또는 예외발생에 관계없이 실행된다. (finally)
 */
@Slf4j
@Aspect
public class AspectV6Advice {

    @Around("hello.aop.order.aop.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            //@Before
            log.info("1 : [트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            //@AfterReturning
            log.info("5 : [트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            //@AfterThrowing
            log.info("4-2 : [트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            //@After
            log.info("8 : [리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }

    @Before("hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("2 : [before] {}", joinPoint.getSignature());
    }

    /**
     * returning : advice 메서드의 매개변수와 이름이 일치해야한다.
     * returning 절에 지정된 타입의 값을 반환하는 메소드만 대상으로 실행된다.
     */
    @AfterReturning(value = "hello.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) {
        log.info("3 : [return] {} return={}", joinPoint.getSignature(), result);
    }

    /**
     * throwing 속성에 사용된 이름은 advice 메서드의 매개변수 이름과 일치해야한다.
     * throwing 절에 지정된 타입과 일치하는 예외가 던져진 메서드만 대상으로 실행한다.
     */
    @AfterThrowing(value = "hello.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("3 : [ex] {} message={}", ex);
    }

    @After(value = "hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("4-1 : [after] {}", joinPoint.getSignature());
    }

}
