package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: A 사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB: B 사용자 10000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // ThreadA: A 사용자 주문 금액 조회
        System.out.println("price = " + userAPrice);     // StatefulService.java 에서 price 값 변경하는 부분을 제거함으로써 알맞게 10000원이 출력된다.
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}