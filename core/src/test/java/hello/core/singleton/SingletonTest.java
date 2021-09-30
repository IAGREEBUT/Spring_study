package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

        //참조값이 다른 것을 확인 ( 객체가 2개 생성됨 )
//        System.out.println("memberService1 = " + memberService1);
//        System.out.println("memberService2 = " + memberService2);

        //테스트 성공을 위해서는 사실 아래와 같이 하는게 맞음
        //memberService != meberService2
        assertThat(memberService1).isNotSameAs(memberService2);


    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용(SingletonService)")
    void singletonServiceTest(){
//        private access불가능하기 때문에 new로 호출 불가능 (컴파일 에러)
//        new SingletonService();

        //1. 조회
        SingletonService singletonService1 = SingletonService.getInstance();//getInstance로 static변수 호출 -> 새로운 객체가 생성되지 않음
        //2. 조회
        SingletonService singletonService2 = SingletonService.getInstance();//getInstance로 static변수 호출 -> 새로운 객체가 생성되지 않음


        // 서로 같은 객체가 출력됨
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);


        //실제 인스턴스가 같은지 비교해야 하므로 isSameAs
        assertThat(singletonService1).isSameAs(singletonService2);


    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){

        //        AppConfig appConfig = new AppConfig(); 직접 만들지 않음

        //스프링 컨테이너를 이용
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //스프링 (싱글톤) 컨테이너를 사용
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 서로 같은 객체가 출력됨
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //실제 인스턴스가 같은지 비교해야 하므로 isSameAs
        assertThat(memberService1).isSameAs(memberService2);


    }


}
