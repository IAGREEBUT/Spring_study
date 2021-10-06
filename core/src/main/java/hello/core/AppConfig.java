package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Spring에서 제공하는 DI 컨테이너
public class AppConfig { //관심사의 분리

    //config를 통해서 memberService / OrderService(인터페이스) 를 조회시 -> 조회하는 애들은 인터페이스와만 의존관계를 가진다! (구현체를 조회한게 아니라 인터페이스를 조회함)
    //config에서 등록한 각각 구현체 MemoryMemberRepo / FixDiscountPolicy 구현체로 생성한
    // memberSerivceImpl / OrderServiceImpl(구현체)를 반환한다.


    @Bean
    public MemberService memberService(){ // 메소드 명 : 스프링 빈의 이름 (key)
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); // 리턴 값 : 스프링 빈의 value
        // MemberServiceImpl을 생성자를 이용해서 생성함 -> 생성시 매개변수로 구현체를 넣어줌
        // MemberServiceImpl자체에서 MemberRepository구현체를 선택하지 않고, 여기서 넣어주기 때문에 DIP/OCP를 지켰다
    }

    @Bean
    public MemberRepository memberRepository() {

        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();//나중에 구현체를 변경할 때에도 쉽게 변경하기 쉬움
    }

    @Bean
    public OrderService orderService(){

        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), disountPolicy());
    }

    @Bean
    public DiscountPolicy disountPolicy(){
        return new RateDiscountPolicy();
//        return new FixDiscountPolicy();
    }



}
