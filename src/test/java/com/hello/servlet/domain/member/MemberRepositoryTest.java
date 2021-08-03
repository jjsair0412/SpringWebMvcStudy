package com.hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    // 한 테스트메서드가 끝날 때 마다 store 초기화
    void afterEach(){
        memberRepository.clearStroe();
    }


    @Test
    void save(){
        // given
        Member member = new Member("kIM",25);

        // when
        Member savedMember = memberRepository.save(member);

        // then
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll(){
        //given
        Member member1 = new Member("KIM",20);
        Member member2 = new Member("jin",21);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2); // 개수 확인
        assertThat(result).contains(member1, member2); // member1번과 member2번이 위치하는지 확인

    }

}