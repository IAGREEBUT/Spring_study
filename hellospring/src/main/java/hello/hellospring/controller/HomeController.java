package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") //사이트 첫 접속 url
    public String home(){
        return "home";//home.html 요청
    }
}
