package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") //작성한 공통 사항을 어디에 적용할 지 명시
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();

        System.out.println("START : "+ joinPoint.toString());
        try{
            return joinPoint.proceed(); //return값이 필요함 ojbect 타입으로 리턴됨
        }finally{
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : "+ joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
