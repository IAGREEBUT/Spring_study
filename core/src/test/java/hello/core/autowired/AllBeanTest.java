package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DisountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    void findAllBean(){
        //의존관계 주입을 위한 AutoAppConfig class , 테스트를 위한 DiscountService class
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountService.class);

    }

    static class DiscountService{//기존 orderService를 손대지 않기 위해 새로운 클래스로 테스트

        private final Map<String, DisountPolicy> policyMap;
        private final List<DisountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DisountPolicy> policyMap, List<DisountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;

            //의존관계 주입 확인을 위해 출력
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

    }

}
