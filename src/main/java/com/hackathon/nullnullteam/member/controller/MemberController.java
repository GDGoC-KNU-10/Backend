package com.hackathon.nullnullteam.member.controller;

import com.hackathon.nullnullteam.global.annotation.Authenticate;
import com.hackathon.nullnullteam.global.dto.GlobalResponse;
import com.hackathon.nullnullteam.member.controller.dto.MemberRequest;
import com.hackathon.nullnullteam.member.controller.dto.MemberResponse;
import com.hackathon.nullnullteam.member.service.MemberService;
import com.hackathon.nullnullteam.member.service.dto.MemberModel;
import com.hackathon.nullnullteam.member.service.dto.MemberModel.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    /*@GetMapping("/login")
    public ResponseEntity<Void> login() {
        String loginUrl = memberService.getCodeUrl();

        return ResponseEntity.status(HttpStatus.SEE_OTHER)
                .header("location", loginUrl)
                .build();
    }*/

    @PostMapping("/callback")
    public ResponseEntity<MemberResponse.Login> registerMember(@RequestBody MemberRequest.Info request) {
        MemberModel.Login memberInfo = memberService.register(request.toCommand());


        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Authorization", memberInfo.jwt())
                .body(MemberResponse.Login.from(memberInfo));
    }

    @GetMapping("")
    public MemberResponse.Info getMemberInfo(@Authenticate Long memberId) {
        Info model = memberService.getMemberInfo(memberId);
        return MemberResponse.Info.from(model);
    }
}
