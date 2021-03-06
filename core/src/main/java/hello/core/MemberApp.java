package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {


    //psvm (단축)
    public static void main(String[] args) {

//3. Spring DI컨테이너 이용 (Bean)
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); //모든 Bean을 관리해주는 것
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); //name(주로 appconfig내 메소드명), return type


        // 2. 수정된 순수 자바 코드 - Appconfig를 이용하여, DIP/OCP문제 해결
        // 구현체의 생성과 의존관계 연결을 담당하는 "appConfig"객체를 생성함
        // appConfig내 memberService(인터페이스)의 "생성+의존연결"을 담당하는 메소드를 호출해 memberServiceImpl(구현체)리턴받아 사용
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();



        //1. 순수 자바 코드 - DIP/OCP문제가 존재
        //main는(클라이언트)에서 memberService 내 메소드를 실행하기 위해 구현체를 만듬
        // memberService는 memberRepository를 의존하기 때문에, MemberServiceImpl코드 내에서 직접 MemoryMemberRepository를 생성해 사용했음.
//        MemberService memberService = new MemberServiceImpl(); // 인터페이스 = 구현체



        // 맴버 객체를 하나 생성
        Member member = new Member(1L, "memberA", Grade.VIP);


        //생성한 멤버를 가입시키기 : MemberService이용
        memberService.join(member);


        //가입시킨 멤버를 검색하기
        Member findMember = memberService.findMember(1L);

        //soutv : 단축

        // 찾은 결과와 넣은 멤버를 비교
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());




    }



}
