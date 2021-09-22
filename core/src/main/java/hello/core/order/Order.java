package hello.core.order;

public class Order {

    private Long memberId;//주문 한 고객 id
    private String itemName;//주문한 상품명
    private int itemPrice;// 주문한 상품 가격
    private int discountPrice; // 할인 적용 금액

    //생성자 (주문이 들어오고 할인 가격까지 계산되었을 때 객체가 생성된다)
    public Order(Long memberId, String itemName, int itemPrice, int discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }


    //최종 가격에 대한 메소드
    public int calculatePrice(){
        return itemPrice - discountPrice; //최종 가격
    }


    //getter & setter
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    //객체를 출력하면 자동으로 toString으로 attribute의 값을 알 수 있음
    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
