package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);


        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //Thread A : A사용자 10000원 주문
        statefulService1.order("userA",10000);

        //Thread B : B사용자 20000원 주문 (주문 금액 조회 사이에 B사용자가 끼어든 상황)
        statefulService2.order("userB",20000);


        //Thread A : A사용자 주문 금액 조회 하려고 함
        int price = statefulService1.getPrice();
        System.out.println("price = " + price); //B사용자의 주문 금액이 나옴


        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000); //안됨 10000원이어야함

    }


    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }

    }

}
