package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class); //컴포넌트 클래스가 파라미터(자동으로 파라미터로 들어간 클래스가 컴포넌트 스캔됨->@Component안써도 )

        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class); //호출 시 생성됨(init호출)

        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class); //호출 시 생성됨(init호출)

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2); //1과 2가 같지 않음이 true여야함

        //만약 destroy가 꼭 필요한 일이 있으면 수동호출 해주어야함
//        prototypeBean1.destroy();
//        prototypeBean2.destroy();

        ac.close();
    }


    @Scope("prototype")//default값
    static class PrototypeBean{

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy(){// 호출되지 않음
            System.out.println("PrototypeBean.destroy");
        }


    }



}
