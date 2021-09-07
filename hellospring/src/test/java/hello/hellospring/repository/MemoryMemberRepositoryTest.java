package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository =  new MemoryMemberRepository();


    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }


    @Test
    public void save(){
        Member member = new Member(); //맴버 객체 생성
        member.setName("yujeongLEE");// name에 값 setting

        repository.save(member); //save method를 실행 -> store에 방금 생성한 member저장


        Member result = repository.findById(member.getId()).get(); // 방금 생성한 member의 ID를 이용해 findById로 맴버가 잘 찾아지는지 확인

//        1. 콘솔창에 출력해보기
//        System.out.println("result = " + (result == member)); -> 내가 생성한 member와 DB에서 가져온 결과가 같으면 "result = true"가 콘솔창에 출력됨

//        2. Assertions 이용 : 둘이 같은지 확인가능 매개변수(expected, actual)
//        Assertions.assertEquals(member, result); // 같은 경우테스트용
//        Assertions.assertEquals(member, null); //다른 경우테스트용

//        3. Assertions 이용(assertj)
        assertThat(member).isEqualTo(result);

    }



    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("yujeongLEE");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("hyunjiLIM");
        repository.save(member2);

        Member result = repository.findByName("yujeongLEE").get();

        assertThat(result).isEqualTo(member1);//member1을 넣으면 성공 / member2를 넣으면 오류 여야함

    }

    @Test
    public void findAll(){

        Member member1 = new Member();
        member1.setName("yujeongLEE");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("hyunjiLIM");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2); //모든 것을 찾아내는 메소드의 사이즈가 2이면 성공해야함

    }


}
