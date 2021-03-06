package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy {


    private int discountFixAmount = 1000; //고정적으로 VIP은 1000원을 할인

    @Override
    public int discount(Member member, int price) {

        if (member.getGrade() == Grade.VIP) { //VIP인 경우에만 fixAmount만큼 할인
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
