package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-string")
    @ResponseBody // http의 body부분에 이 데이터를 직접 넣어 주겠다는 의미
    public String helloString(@RequestParam("name") String name){
        return "hello" + name; //viewResolver가 아니고 data가 그대로 전달됨
    }


    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(); //Hello객체 생성

        hello.setName(name); //파라미터로 넘어온 name을 이용하여 데이터를 넣음
        return hello; //객체 반환
    }


    static class Hello { //class내에 class를 정의할 수 있음 HelloController.Hello
        private String name;


        //getter -> getName함수로 데이터를 꺼내 사용
        public String getName(){
            return name;
        }

        //setter -> setName함수로 데이터를 입력
        public void setName(String name){
            this.name = name;
        }
    }

}
