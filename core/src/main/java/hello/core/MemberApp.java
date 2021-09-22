package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {


    //psvm (단축)
    public static void main(String[] args) {

        MemberService memberService = new MemberServiceImpl(); // 인터페이스 = 구현체


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
