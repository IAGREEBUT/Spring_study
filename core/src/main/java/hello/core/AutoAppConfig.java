package hello.core;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( //@Component annotation이 붙은 클래스를 자동으로 스캔해서 스프링 빈으로 등록해줌,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        //@Component 가 붙은 클래스 중 자동스캔에서 @Configuration이 붙은 것은 제외할 것(AppConfig->@Configuration(얘도 내부에 @Component가 있음) 때문에 작성된부분)
)
public class AutoAppConfig {




}
