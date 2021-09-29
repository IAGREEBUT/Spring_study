package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){

        MemberService memberService = ac.getBean("memberService", MemberService.class); //매개변수 2개(이름, 타입)
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); //memberServiceImpl가 MemberSerive의 객체인가

    }



    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class); //매개변수 1개 (타입)
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); //memberServiceImpl가 MemberSerive의 객체인가
    }


    @Test
    @DisplayName("구체 타입으로만 조회")
    void findBeanByName2(){
        MemberService memberService = ac.getBean(MemberServiceImpl.class); //매개변수 1개 (구체적인 구현객체 타입, 유연성이 떨어짐) -> 비추천 : 역할에 의존하는게 좋음
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); //memberServiceImpl가 MemberSerive의 객체인가
    }

    @Test
    @DisplayName("빈 이름으로 조회X") //실패 테스트
    void findBeanByNameX(){

        assertThrows(NoSuchBeanDefinitionException.class, ()-> ac.getBean("xxxxx", MemberService.class));

    }



}
