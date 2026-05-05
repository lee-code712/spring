package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        // 수정자 주입을 사용할 경우 안에서 생성 되어야 할 객체 생성이 안돼서 오류 발생 -> 생성자 주입을 사용하자
        // 생성자 주입 사용 시 final 키워드 사용 가능 -> 초기화 누락을 막음
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy()); 
        Order order = orderService.createOrder(1L, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}