package hello.core.singleton;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    @DisplayName("빈 이름으로 조회")
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //threadA: A사용자가 10,000원 주문
        int price1 = statefulService1.order("userA", 10000);
        //threadB: B사용자가 20,000원 주문
        int price2 = statefulService2.order("userB", 20000);

        //이제 price1, price2는 지역변수이므로 각각 값을 조회

        //ThreadA: A사용자의 주문 금액 조회
//        int price = statefulService1.getPrice(); //예상결과:10000
//        System.out.println("price = " + price); //실제결과:20000
        //이유는? statefulService1 == statefulService2 (같은 객체)

        System.out.println("price1 = " + price1);

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}