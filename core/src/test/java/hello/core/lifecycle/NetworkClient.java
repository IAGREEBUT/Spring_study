package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct; //javax 자바에서 공식적으로 지원(스프링 아닌 다른 자바기반 프레임워크에서도 사용가능)
import javax.annotation.PreDestroy;

public class NetworkClient{


    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = "+url);
    }


    // url field setter
    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect: "+ url);
    }

    // 메세지
    public void call(String message){
        System.out.println("call " + url + "message = " + message);//어디로 보낼지, 무슨 메세지인지
    }

    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("close: "+url);
    }

    @PostConstruct
    public void init() { //property setting(의존관계주입)이 끝나면 호출
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close(){ // 빈 종료시 호출
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
