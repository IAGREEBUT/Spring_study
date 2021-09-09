package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 순수한 자바코드이기 때문에 스프링 컨테이너가 알 방법이 없음 -> 스프링이 올라올떄 서비스임을 인식하고, 스프링컨테이너의 맴버서비스에 등록해줌.

public class MemberService {


    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }



//    회원가입 기능
    public long join(Member member){

        //같은 이름이 있는 중복 회원X
        validateDuplicateMember(member);


        memberRepository.save(member);
        return member.getId(); //ID반환
    }

// 중복회원 검사
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
        .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }


    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // id를 이용한 1명의 회원 조회
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
