package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {

    //참고 : ApplicationContext에는 getBeanDefinition이 제공되지 않기 때문에, 타입으로 AnnotationConfigApplicationContext를 사용함
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole()== BeanDefinition.ROLE_APPLICATION){ //양이 너무 많으니까 우리가 생성한 빈만 출력
                System.out.println("beanDefinitionName = " + beanDefinitionName + " beanDefinition = "+ beanDefinition);

            }
        }
    }

}
