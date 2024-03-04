package hello.aop.member;

import hello.aop.member.annotation.ClassAop;
import hello.aop.member.annotation.MethodAop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@ClassAop
@Component
@Slf4j
public class MemberServiceImpl implements MemberService {

    @Override
    @MethodAop("test value")
    public String hello(String param) {
        log.info("hello : 타켓 메서드 로직 실행!");
        return "ok";
    }

    public String internal(String param) {
        log.info("internal : 타켓 메서드 로직 실행!");
        return "ok";
    }
}
