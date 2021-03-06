package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        //각자 다른 prototypeBean의 count field가 1인지

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);


        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class); //매개변수에 있는것 스프링 빈으로 자동등록


        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1); //count 0->1


        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);//count 1->2


    }


    @Scope("singleton")
    static class ClientBean{

//        private final PrototypeBean prototypeBean;

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider; //


//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean){ //이때 스프링 컨테이너에 PrototypeBean을 요청-> 그떄 만들어져서 ClientBean(싱글톤)에 할당  (생성시점에 주입)
//            this.prototypeBean = prototypeBean;
//        }

//        @Autowired
//        ApplicationContext applicationContext;

        public int logic(){
//            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class); //이 처럼 logic이 실행될 때 마다 항상 요청하면 원하는 대로 만들 수 있다
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }

    }





    @Scope("prototype")
    static class PrototypeBean{

        private int count = 0;

        public void addCount(){
            count++;
        }

        private int getCount(){
            return count;
        }


        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }


    }


}
