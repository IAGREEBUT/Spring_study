package hello.core.order;

public interface OrderService {

    //주문 생성 시 (주문자 id, 아이템 명, 아이템 가격)을 넘겨주기로 했음
    Order creatOrder(Long memberId, String itemName, int itemPrice);

}
