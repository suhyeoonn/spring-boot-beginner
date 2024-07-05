package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() { // 테스트코드는 한글로도 많이 적음
        // given: 무언가 주어졌는데 / when: 이걸 실행했을 때 / then: 결과가 이게 나와야 함
        //given
        Member member1 = new Member();
        member1.setName("member1");

        //when
        Long saveId = memberService.join(member1);

        //then
        Member result = memberService.findOne(saveId).get();
        assertThat(member1.getName()).isEqualTo(result.getName());

    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("member1");

        Member member2 = new Member();
        member2.setName("member1");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}