package hello.core.singleton;

public class SingletonService {


    //static으로 선언하여, 공유할 수 있도록 만들고 -> 자바가 올라갈 때 생성되는 변수
    private static final SingletonService instance = new SingletonService(); //자기자신을 내부에 private 객체로 가짐 -> 특히 static으로 선언하면, 클래스 레벨에 올라가 1개만 존재하게 됨


    // 이 메서드를 이용해서 해당 공유변수를 조회한다.
    public static SingletonService getInstance(){
        return instance;
    }


    //private 생성자를 이용해 다른 클래스에서(내부 클래스에서는 당연히 호출가능) new로 새로운 객체를 생성하는 것을 막는다.
    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }



}
