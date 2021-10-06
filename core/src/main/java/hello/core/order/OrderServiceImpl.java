package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

//1. 최초 순수 자바 클래스 version -> 문제점 : 추상뿐 아니라 구현 클래스에도 의존
//    private final DisountPolicy disountPolicy = new FixDiscountPolicy();
//    private final DisountPolicy disountPolicy = new RateDiscountPolicy();


//2. 스프링 컨테이너 사용 (문제점해결 / 의존관계 주입 - 생성자 주입방법)
    private final MemberRepository memberRepository;// -> 오직 인터페이스만 존재 (추상화에만 의존함 DIP)
    private final DiscountPolicy discountPolicy; //인터페이스 (추상)에만 의존함 (DIP)
    // 어떤 구현체가 들어올지 전혀 모르는 상태다


    @Autowired// 자동으로 의존관계(MemberRepo , discountpolicy)를 주입해줌
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy){
        this.discountPolicy = discountPolicy;
        this.memberRepository=memberRepository;
    }

//3. 스프링 컨테이너 사용(의존관계 주입 - setter주입)
//    private MemberRepository memberRepository;
//    private DisountPolicy disountPolicy;
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DisountPolicy disountPolicy){
//        this.disountPolicy = disountPolicy;
//    }

    @Override
    public Order creatOrder(Long memberId, String itemName, int itemPrice) { //1. 주문 생성

        // 2. 회원 조회
        Member member = memberRepository.findById(memberId);


        // 3. 할인 적용
        //할인에 대한 내용은 Order에서 신경쓰지 않고 결과만 사용한, discountPolicy에게 일임함 -> 잘 된 설계 : 단일 체계의 원칙이 잘 지켜짐
        // 할인에 대한 변경이 들어올 때 OrderService는 한줄도 변화하지 않는 상태임
        int discountPrice = discountPolicy.discount(member, itemPrice); //최종적으로 할인된 가격

        // 4. 주문 결과 반환
        return new Order(memberId, itemName, itemPrice, discountPrice); // 주문 생성하여 반환됨
    }



    //테스트용도 (29. @Configuration과 싱글톤 단원)
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
