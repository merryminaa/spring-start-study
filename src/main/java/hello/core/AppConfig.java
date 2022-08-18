package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //설정정보
public class AppConfig {

    // @Bean memberService -> new MemoryMemberRepository();
    // @Bean orderService -> new MemoryMemberRepository();
    // 이렇게된다면 각각 다른 MemoryMemberRepository가 생성되면서 싱글톤이 깨지는건 아닐까?

    @Bean //스프링 컨테이너에 등록ㄴ
    //애플리케이션의 실제 동작에 필요한 구현 객체를 생성
    //생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)
    //메서드 이름만으로도 역할과 전체구성을 알 수 있도록 리팩토링(중복제거, 이후 구현체 변경시에도 변경코드 축소)
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository()); //생성자 주입
    }


    @Bean
    private static MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy(); //변경시 구성 영역만 변경하면 OK (사용영역에 변경 영향 x)
    }
}
