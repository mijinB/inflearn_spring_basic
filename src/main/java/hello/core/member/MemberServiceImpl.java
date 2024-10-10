package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    // 인터페이스(MemberRepository)를 의존하지만 구현 클래스(MemoryMemberRepository)도 의존하기 때문에 DIP 원칙 위반이다.
    // 저장소를 변경하려면 아래 코드를 수정해야 하기 때문에 OCP 원칙도 위반한다.
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    // ↓ 인터페이스만 의존하고 있다. DIP 원칙 지키는 것. ⇒ 생성자 주입
    private final MemberRepository memberRepository;

    @Autowired      // == ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // Test 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
