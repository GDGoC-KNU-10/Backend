package com.hackathon.nullnullteam.member.service;

import com.hackathon.nullnullteam.member.Gender;
import com.hackathon.nullnullteam.member.Member;
import com.hackathon.nullnullteam.member.infrastructure.apicaller.dto.KakaoAccount;
import com.hackathon.nullnullteam.member.infrastructure.apicaller.dto.UserInfoResponse;
import com.hackathon.nullnullteam.member.infrastructure.repository.MemberRepository;
import com.hackathon.nullnullteam.member.service.dto.MemberCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MemberWriterService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member saveMember(MemberCommand.Info command) {
//        KakaoAccount kakaoAccount = userInfoResponse.kakaoAccount();
        Member member = memberRepository.save(
                Member.builder()
                        .name(command.name())
                        .age(command.age())
                        .gender(Gender.fromString(command.gender()))
                        .profile(command.profile())
                        .email(command.email())
                        .build());
        return member;
    }
}
