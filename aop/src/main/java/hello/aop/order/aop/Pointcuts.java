package hello.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * 포인트컷을 공용으로 사용하고 싶다면 외부의 별도 클래스에 모아두고 사용할 수 있다.
 * 이때, 포인트컷의 접근 제어자는 public으로 열어두어야 한다.
 */
public class Pointcuts {

    //hello.aop.order 패키지와 하위 패키지
    @Pointcut("execution(* hello.aop.order..*(..))")
    public void allOrder() {
    } //pointcut signature

    //클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {
    }

    //allOrder && allService
    @Pointcut("allOrder() && allService()")
    public void orderAndService() {
    }

}
