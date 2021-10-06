package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    void findAllBean(){
        //의존관계 주입을 위한 AutoAppConfig class , 테스트를 위한 DiscountService class
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP); //discountService사용을 위한 vip 멤버 생성
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy"); // fixDiscount적용 선택
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);// fixDiscount 적용시 1000원할인 인지 확인


        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy"); // rateDiscount적용 선택
        assertThat(rateDiscountPrice).isEqualTo(2000);//rate discount 적용시 2000원 할인인지 확인

    }

    static class DiscountService{//기존 orderService를 손대지 않기 위해 새로운 클래스로 테스트

        private final Map<String, DiscountPolicy> policyMap; //모든 discountpolicy를 주입 (fix, rate)
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {//의존성 주입 Map
            this.policyMap = policyMap;
            this.policies = policies;

            //의존관계 주입 확인을 위해 출력
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }


        public int discount(Member member, int price, String discountCode){
            DiscountPolicy discountPolicy = policyMap.get(discountCode); //map에서 둘 중 하나의 로직을 선택
            return discountPolicy.discount(member,price);//해당 로직으로 할인 진행
        }
    }

}
