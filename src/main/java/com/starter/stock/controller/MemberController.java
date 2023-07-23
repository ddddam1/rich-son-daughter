package com.starter.stock.controller;

import com.starter.stock.domain.Member;
import com.starter.stock.domain.TokenInfo;
import com.starter.stock.dto.MemberLoginRequestDto;
import com.starter.stock.repository.MemberRepository;
import com.starter.stock.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping("/issue")
    public String issueGuestToken(@RequestParam String temporaryUserName) {
        String memberId = temporaryUserName;
        String password = "";
        TokenInfo tokenInfo = memberService.login(memberId, password);
        return tokenInfo.getAccessToken();
    }

    @PostMapping("/login")
    public TokenInfo login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
        String memberId = memberLoginRequestDto.getMemberId();
        String password = memberLoginRequestDto.getPassword();
        TokenInfo tokenInfo = memberService.login(memberId, password);
        return tokenInfo;
    }

    @PostMapping("/change")
    @Transactional
    public String changeUserName(Long id, String newName) {
        Optional<Member> byId = memberRepository.findById(id);
        if (byId.isPresent()) {
            Member member = byId.get();
            member.setName(newName);
            return member.getName();
        } else {
            return "정보가 없습니다.";
        }
    }
}
