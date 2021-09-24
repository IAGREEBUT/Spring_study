package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {


    public static void main(String[] args) {


        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);


//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();



//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService =  new OrderServiceImpl();


        //주문을 진행할 회원 부터 생성
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);


        //가진 정보를 가지고 주문
        Order order = orderService.creatOrder(memberId, "itemA",10000);

        //생성된 주문 출력
        System.out.println("order = " + order );
        System.out.println("order Calculate = " + order.calculatePrice()); //지불해야할 금액


    }



}
