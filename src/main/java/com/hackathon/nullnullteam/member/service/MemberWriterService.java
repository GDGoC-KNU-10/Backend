package com.hackathon.nullnullteam.member.service;

import com.hackathon.nullnullteam.member.Gender;
import com.hackathon.nullnullteam.member.Member;
import com.hackathon.nullnullteam.member.infrastructure.apicaller.dto.KakaoAccount;
import com.hackathon.nullnullteam.member.infrastructure.apicaller.dto.UserInfoResponse;
import com.hackathon.nullnullteam.member.infrastructure.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MemberWriterService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member saveMember(UserInfoResponse userInfoResponse) {
        KakaoAccount kakaoAccount = userInfoResponse.kakaoAccount();
        Member member = memberRepository.save(
                Member.builder()
                        .name(kakaoAccount.name())
                        .email(kakaoAccount.email())
                        .age(LocalDate.now().getYear() - Integer.parseInt(kakaoAccount.birthYear()))
                        .gender(Gender.fromString(kakaoAccount.gender()))
                        .build());
        return member;
    }
}
