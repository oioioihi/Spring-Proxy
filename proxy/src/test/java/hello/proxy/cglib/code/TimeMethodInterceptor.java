package hello.proxy.cglib.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * JDK 동적 프록시에 실행 로직을 위해 'InvocatioinHandler'를 제공했듯이, CGLIB는 'MethodInterceptor'를 제공한다.
 */
@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {

    private final Object target;

    public TimeMethodInterceptor(Object target) {
        this.target = target;
    }

    /**
     * @param obj         : CGLIB가 적용된 객체
     * @param method      : 호출된 메서드
     * @param args        : 메서드를 호출하면서 전달된 인수
     * @param methodProxy : 메서드 호출에 사용
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        Object result = methodProxy.invoke(target, args); // method를 사용해서 호출해도 되지만, methodProxy를 사용하는 것이 성능이 더 좋다고 함.

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeProxy 종료 resultTime={}", resultTime);
        return result;
    }
}
