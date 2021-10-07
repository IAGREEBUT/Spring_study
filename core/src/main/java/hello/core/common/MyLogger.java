package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;


@Component
@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS)//HTTP 요청당 1개씩 생성, HTTP요청이 끝나면 소멸
public class MyLogger {

    private String uuid;
    private String requestURL; //빈이 생성되는 순간 값이 들어오지 않고 외부에서 값을 받아 setter를 이용하여 입력받음

    public void setRequestURL(String requestURL){
        this.requestURL=requestURL;
    }


    public void log(String message){
        System.out.println("[ "+uuid+" ]" + "[ " + requestURL + " ] "+message);
    }

    @PostConstruct
    public void init(){ //최초로 스프링에 이 클래스가 요청될 시 호출됨
        uuid = UUID.randomUUID().toString(); //절대로 겹치지 않은 번호
        System.out.println("[ "+uuid+" ] request scope bean create: "+this);//주소 출력
    }

    @PreDestroy
    public void close(){//요청에 대한 답변이 나가면 호출됨
        System.out.println("[ "+uuid+" ] request scope bean close: "+this);//주소 출력

    }



}
