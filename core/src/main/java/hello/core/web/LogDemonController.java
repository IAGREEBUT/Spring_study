package hello.core.web;


import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor //final붙은 애들 생성자 생략해도 자동의존관계 주입
public class LogDemonController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;
//    private final ObjectProvider<MyLogger> myLoggerProvider; // 스프링 컨테이너가 의존관계 주입을하려고 하는데 request scope라 myLogger가없음 (HTTP요청이 들어와야 생성되기 때문) -> Provider로 해결


    @RequestMapping("log-demo")// log-demo요청이 들어
    @ResponseBody //view 탬플릿을 거치지 않고 문자를 응답으로 반환
    public String logDemo(HttpServletRequest request) throws InterruptedException { //고객요청정보를 받아옴
        String requestURL = request.getRequestURI().toString(); //고객이 요청한 값 저장
//        MyLogger myLogger = myLoggerProvider.getObject(); //Provider를 이용해 필요한 시점(클라이언트 요청을 받아옴) 에 myLogger빈을 받아옴

        System.out.println("myLogger = "+myLogger.getClass() ); //myLogger proxy 확인을 위한 출력
        myLogger.setRequestURL(requestURL);


        //컨트롤러에 로그를 남김
        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }

}
