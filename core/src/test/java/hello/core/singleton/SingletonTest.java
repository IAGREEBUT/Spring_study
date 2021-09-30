package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {


    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        //1. 조회 : 호출할 때 마다 객체 생성
        MemberService memberService1 = appConfig.memberService();//memberService 메소드 호출 -> new로 객체생성 후 반환
        //2. 조회 : 호출할 때 마다 객체 생성
        MemberService memberService2 = appConfig.memberService(); //memberService 메소드 호출 -> new로 객체생성 후 반환

        //참조값이 다른 것을 확인 ( 객체가 2개 생성됨 ) -> 출력으로 확인하는 것은 테스트에서 안좋은 것 따라서 아래 처럼 변경해야함
//        System.out.println("memberService1 = " + memberService1);
//        System.out.println("memberService2 = " + memberService2);

 
    }


}
