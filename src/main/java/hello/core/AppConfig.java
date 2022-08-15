package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
    //애플리케이션의 실제 동작에 필요한 구현 객체를 생성
    //생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)
    //메서드 이름만으로도 역할과 전체구성을 알 수 있도록 리팩토링(중복제거, 이후 구현체 변경시에도 변경코드 축소)

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository()); //생성자 주입
    }

    private static MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
