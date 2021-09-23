package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {


    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){ // 코드 실행전에 무조건 실행되는 코드
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }


//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService =  new OrderServiceImpl();


    @Test
    void createOrder() {

        Long memberId = 1L; // long type은 NULL불가 wrapper type인 Long사용
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);


        //가진 정보를 가지고 주문
        Order order = orderService.creatOrder(memberId, "itemA",10000);


        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);


    }



}
