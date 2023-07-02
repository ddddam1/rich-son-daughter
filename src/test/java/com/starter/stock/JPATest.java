package com.starter.stock;

import com.starter.stock.domain.Member;
import com.starter.stock.domain.Role;
import com.starter.stock.repository.MemberRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JPATest {
    @Autowired MemberRepository memberRepository;

    @Test
    @Rollback(false)  // 롤백하지 않음
    public void findUser() {
        Member guest = new Member();
        guest.setName("강묵봉");
        guest.setRole(Role.GUEST.name());
        guest.setPassword("");
        memberRepository.save(guest);

        System.out.println(guest.getMemberId());
        System.out.println(guest.getUsername());
        Optional<Member> byId = memberRepository.findById(guest.getMemberId());
        byId.get().setName("김소담");

        Assertions.assertEquals("김소담", byId.get().getName());
    }
}
