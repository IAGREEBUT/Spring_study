package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

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


    public void init() { //property setting(의존관계주입)이 끝나면 호출
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메세지");
    }

    public void close(){ // 빈 종료시 호출
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
