package com.hackathon.nullnullteam.member.service;

import com.hackathon.nullnullteam.global.auth.JwtProvider;
import com.hackathon.nullnullteam.member.Member;
import com.hackathon.nullnullteam.member.infrastructure.apicaller.MemberApiCaller;
import com.hackathon.nullnullteam.member.infrastructure.apicaller.dto.KakaoAccount;
import com.hackathon.nullnullteam.member.infrastructure.apicaller.dto.TokenInfoResponse;
import com.hackathon.nullnullteam.member.infrastructure.apicaller.dto.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberApiCaller memberApiCaller;
    private final JwtProvider jwtProvider;
    private final MemberReaderService memberReaderService;
    private final MemberWriterService memberWriterService;


    public String getCodeUrl() {
        return memberApiCaller.createCodeUrl();
    }

    @Transactional
    public String register(String code) {
        // 토큰 발급
        TokenInfoResponse tokenResponse = memberApiCaller.getAccessToken(code);
        String accessToken = tokenResponse.accessToken();
        // 카카오 사용자 정보 요청
        UserInfoResponse userInfoResponse = memberApiCaller.extractUserInfo(accessToken);
        KakaoAccount kakaoAccount = userInfoResponse.kakaoAccount();

        // Users 저장 및 중복 체크
        Member member = memberReaderService.findByEmail(kakaoAccount.email())
                .orElseGet(() -> {
                    Member newMember = memberWriterService.saveMember(userInfoResponse);
                    return newMember;
                });

        String token = jwtProvider.createToken(member.getId(), member.getEmail());
        System.out.println(token);
        return token;
    }
}
