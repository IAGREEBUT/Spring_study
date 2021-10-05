package hello.core.order;

import org.junit.jupiter.api.Test;

public class OrderServiceImplTest {

     @Test
    void createOrder(){
         OrderServiceImpl orderService = new OrderServiceImpl();
         orderService.creatOrder(1L, "itemA", 10000);
     }


}
