package hello.core.member;


public class MemberServiceImpl implements MemberService {

    // " 인터페이스 = 구현체 " 형태
    // 의존관계에는 구현체가 아닌 인터페이스를 의존해야하며,
    // 구현객체가 없으면 nullpointException이므로 구현체를 선택해줘야한다.
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member); //Repository에서 save함수를 입력받은 member매개변수를 가지고 실행
    }


    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId); //Repository에서 findById함수를 입력받은 memberId 매개변수를 가지고 실행
    }



}
