package hello.core.singleton;

public class StatefulService {


//    private int price; //상태를 유지하는 필드

    public int order(String name, int price){

        System.out.println("name = " + name + " price = "+price);

//        this.price = price; //여기가 문제! 공유필드가 변경됨
        return price; //지역변수로 넘겨버리기

    }

//    public int getPrice(){
//        return price;
//    }


}
