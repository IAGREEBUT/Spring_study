package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository; //인터페이스

import java.util.Optional;

// 인터페이스 스프링 제이피아이가 구현체가 자동으로 만들어줌 그걸 가져다쓰기만하면됨
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>,MemberRepository { //자료형 <class T,ID 식별자pk자료형>

    Optional<Member> findByName(String name);

}
