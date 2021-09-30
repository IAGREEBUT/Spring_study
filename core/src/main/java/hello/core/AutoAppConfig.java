package hello.core;


import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( //@Component annotation이 붙은 클래스를 자동으로 스캔해서 스프링 빈으로 등록해줌,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        //@Component 가 붙은 클래스 중 자동스캔에서 @Configuration이 붙은 것은 제외할 것(AppConfig->@Configuration(얘도 내부에 @Component가 있음) 때문에 작성된부분)
)
public class AutoAppConfig {

    // 34. 중복 등록과 충돌
    // 수동 빈 등록이 자동 빈 등록과 이름이 같은 경우 테스트를 위해 자동 빈 등록에 있는 이름과 같은 것을 아무거나 빈으로 등록해봄 
//    @Bean(name = "memoryMemberRepository")
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }


}
