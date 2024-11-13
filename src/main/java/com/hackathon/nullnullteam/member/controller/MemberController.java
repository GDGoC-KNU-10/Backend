package com.hackathon.nullnullteam.member.controller;

import com.hackathon.nullnullteam.global.dto.GlobalResponse;
import com.hackathon.nullnullteam.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public ResponseEntity<Void> login() {
        String loginUrl = memberService.getCodeUrl();

        return ResponseEntity.status(HttpStatus.SEE_OTHER)
                .header("location", loginUrl)
                .build();
    }

    @GetMapping("/callback")
    public ResponseEntity<GlobalResponse> registerMember(
            @RequestParam("code") String code
    ) {
        String token = memberService.register(code);

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Authorization", token)
                .body(GlobalResponse.builder().message("회원가입이 완료되었습니다.").build());
    }
}
