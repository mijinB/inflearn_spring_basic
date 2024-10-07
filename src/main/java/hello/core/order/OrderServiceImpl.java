package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    /* DIP(의존관계 역전) 원칙 위반
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); */
    // 해결 ↓
    private DiscountPolicy discountPolicy;
    // ↳ NullPointerException 발생 ⇒ OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해주어야 해결된다.

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
