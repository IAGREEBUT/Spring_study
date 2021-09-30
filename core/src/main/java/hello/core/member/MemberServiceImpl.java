package hello.core.member;


public class MemberServiceImpl implements MemberService {

    // " 인터페이스 = 구현체 " 형태
    // 의존관계에는 구현체가 아닌 인터페이스를 의존해야하며,
    // 구현객체가 없으면 nullpointException이므로 구현체를 선택해줘야한다.
    private final MemberRepository memberRepository; // 구현체를 직접 선택하지 않도록 함 -> 오직 인터페이스만 존재 (추상화에만 의존함 DIP)

    //생성자
    public MemberServiceImpl(MemberRepository memberRepository){ //생성될 때 매개변수로 MemberRepsository구현체를 받아서 생성됨(생성자주입)  -> 구현체를 매개로 넣어주는일은 AppConfig에서
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member); //Repository에서 save함수를 입력받은 member매개변수를 가지고 실행
    }


    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId); //Repository에서 findById함수를 입력받은 memberId 매개변수를 가지고 실행
    }

    //테스트용도 (29. @Configuration과 싱글톤 단원)
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }



}
