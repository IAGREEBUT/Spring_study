package hello.core.member;

public interface MemberRepository {

    //회원을 db에 저장하는 기능
    void save(Member member);


    //id를 이용해 회원을 찾는 기능
    Member findById(Long memberId);

}
