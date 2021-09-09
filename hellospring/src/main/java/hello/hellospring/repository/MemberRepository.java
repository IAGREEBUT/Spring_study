package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface MemberRepository {

    Member save(Member member); //회원 등록
    Optional<Member> findById(Long id); // 회원 조회 - ID
    Optional<Member> findByName(String name); // 회원 조회 - Name
    List<Member> findAll();
}
