package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {


    //GET, POST등의 method할 때 Get임
    @GetMapping("hello") // "/hello"를 입력하면 method가 호출됨
    public String hello(Model model) {
        model.addAttribute("data","hello!!");
        return "hello";
    }


    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        //parameter를 요청 , 정보를 담아서 view로델 보낼 모델


        model.addAttribute("name",name);// 받은 파라미터를 이용해서 name키의 값으로 지정
        return "hello-template"; //hello-template.html로
    }


}
