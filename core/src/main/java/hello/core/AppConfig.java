package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig { //관심사의 분리

    //config를 통해서 memberService / OrderService(인터페이스) 를 조회시 -> 조회하는 애들은 인터페이스와만 의존관계를 가진다! (구현체를 조회한게 아니라 인터페이스를 조회함)
    //config에서 등록한 각각 구현체 MemoryMemberRepo / FixDiscountPolicy 구현체로 생성한
    // memberSerivceImpl / OrderServiceImpl(구현체)를 반환한다.



    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
        // MemberServiceImpl을 생성자를 이용해서 생성함 -> 생성시 매개변수로 구현체를 넣어줌
        // MemberServiceImpl자체에서 MemberRepository구현체를 선택하지 않고, 여기서 넣어주기 때문에 DIP/OCP를 지켰다
    }

    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }




}
