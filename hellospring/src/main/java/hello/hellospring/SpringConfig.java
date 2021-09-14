package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {


//    private DataSource dataSource; //jdbc레포에 필요한 매개변수
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    //jpa는 entityManager를 사용!
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em){
//        this.em = em;
//    }

//spring api
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) { //스프링 컨테이너에서 멤버리포지토리를 찾음(spring jpa가 자동으로 생성한)
        this.memberRepository = memberRepository;
    }


    @Bean
    public MemberService memberService() {

        return new MemberService(memberRepository);//의존성 등록
    }

//    @Bean
//    public MemberRepository memberRepository(){

//        return new JpaMemberRepository(em);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);

}


