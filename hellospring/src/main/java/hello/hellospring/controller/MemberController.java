package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

//    private final MemberService memberService = new MemberService(); // new로 새로운 객체를 생성하지 않고 하나를 만들어 공유함 -> 등록하여 사용

    // DI 3가지 방법
//    1. 필드 주입
//    @Autowired private MemberService memberService;

//2. 생성자 주입
//    private MemberService memberService;
//
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }


    private final MemberService memberService;
    //3. 생성자 주입
    @Autowired // 맴버 컨트롤러가 스프링컨테이너가 뜰떄 생성됨, 그떄 생성자를 호출하게되는데 생성자에 autowied가 잇으면 맴버서비스를 스프링이 스프링컨테이너에 있는 맴버서비스에서 가져다가 연결시킴 -> 스프링 컨테이너에서 가져오는데
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
