package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy // 어노테이션으로 qualifier 지정, 어노테이션이므로 잘못 입력 시 확인 가능 
// @Primary // 우선순위 지정, 메인/보조 구조에서 많이 사용
// @Qualifier("mainDiscountPolicy") // 문자열이므로 잘못 입력하는 경우 컴파일 타임에 오류 확인 불가
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        }
        return 0;
    }
}
