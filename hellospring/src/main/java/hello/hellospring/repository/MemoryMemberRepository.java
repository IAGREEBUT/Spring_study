package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.lang.reflect.Array;
import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); // 데이터를 저장할 Map 자료형
    private static long sequence = 0L; // ID용

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //ID setting
        store.put(member.getId(),member); // Map에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //결과가 없다면
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
