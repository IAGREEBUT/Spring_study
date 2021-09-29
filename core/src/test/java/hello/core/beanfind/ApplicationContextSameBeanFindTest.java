package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {


    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);


    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다. ")
    void findBeanByTypeDuplicate(){
//        MemberRepository bean = ac.getBean(MemberRepository.class);

        //예외가 발생해도 성공이도록
        assertThrows(NoUniqueBeanDefinitionException.class, ()-> ac.getBean(MemberRepository.class));

    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다. ")
    void findBeanByName(){
        MemberRepository memberRepository = ac.getBean("memberRepository1",MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }


    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class); //여러개일 경우 모두 반환(맵형식)

        for (String key : beansOfType.keySet()) { //맵 전부 출력해보기 (구현객체)
            System.out.println("key = " + key + " value = "+ beansOfType.get(key));
        }

        System.out.println("beanOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2); // MemberRepository가 2개이므로
    }



    @Configuration //이 클래스 내에서만 사용할 클래스 static 사용
    static class SameBeanConfig{ //AppConfig변경하기 곤란해서 임시로 (SameBeanFind)테스트에서만 사용할 용도의 Configuration


        //이런 경우가 있을 수 있음, 특히 파라미터 값이 다른경우 -> 예를 들어 한번에 몇개를 저장할지 파라미터로 받는 경우가 그 예 (하나는 10개 하나는 100개씩 받을 수 있음)
        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }


    }



}
