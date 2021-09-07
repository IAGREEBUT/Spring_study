package hello.hellospring.service;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //테스트하려는 클래스 객체를 생성
    MemberService memberService = new MemberService();


    @Test
    void 회원가입() {

        //given
        //가입시키려는 멤버와 필수요소인 이름이 필요
        Member member = new Member();
        member.setName("hello");

        //when
        //회원 가입 상황(join 메소드이용)
        Long saveId = memberService.join(member);

        //then
        //회원 가입 한 멤버의 id(savdId)로 검색해서 나온 결과의 멤버(findMember)의 이름과 given에서 입력받은 name이 같아야함
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());


    }

    @Test
    public void 중복_회원_예외() { //name이 같은 회원이 가입을 원할 때 -> 가입 예외 메세지 떠야함
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        //name이 같은 회원이 가입을 원할 때
        memberService.join(member1);

        //assertThrows(코드 실행결과로 기대되는 결과(실행되어야 하는 예외 ),실행할 코드 )
        //cmd option v
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


//
//        try{
//            memberService.join(member2);
//            fail(); //exception이 뜨지 않고 넘어가면 실패!
//        }catch(IllegalStateException e){
//            //예외가 발생하여 catch부분이 실행되었으면 성공
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); //예외 발생시 출력시켰던 에러메세지와 같은 것이 출력되는지
//        }

        //then


    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
