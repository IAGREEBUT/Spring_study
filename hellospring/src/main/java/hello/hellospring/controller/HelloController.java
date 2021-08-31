package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {


    //GET, POST등의 method할 때 Get임
    @GetMapping("hello") // "/hello"를 입력하면 method가 호출됨
    public String hello(Model model) {
        model.addAttribute("data","hello!!");
        return "hello";
    }
}
