package hello.core.member;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository {


    // 저장소를 만듬 -> 저장소 확정이 안되었기에 임시로 map으로 구현
    private static Map<Long, Member> store = new HashMap<>(); //동시성을 고려하려면 concurrentHashMap을 사용해아한다.(실무에서)


    //인터페이스를 구현한 것이므로 Override
    @Override
    public void save(Member member) {
        store.put(member.getId(), member); //데이터를 store에 저장
    }

    @Override
    public Member findById(Long memberId) {

        return store.get(memberId); //id를 이용해 db에서 조회함
    }

}
