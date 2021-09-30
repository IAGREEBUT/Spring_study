package hello.core.scan.filter;

import hello.core.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppConfigTest {


    @Test
    void filterScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        BeanA beanA = ac.getBean("beanA", BeanA.class); // include하여 컴포넌트 스캔대상에 포함되어 빈으로 등록되어야 정상
        assertThat(beanA).isNotNull();


//        ac.getBean("beanB", BeanB.class); //exclude하여 컴포넌트 스캔대상에서 제외되어 등록되지 않아야 정상 -> 에러
        assertThrows(
                NoSuchBeanDefinitionException.class,
                ()->ac.getBean("beanB", BeanB.class)
        );
    }


    @Configuration
    @ComponentScan(
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class), //포함시킬 것
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)//제외시킬 것
    )
    static class ComponentFilterAppConfig{

    }

}
