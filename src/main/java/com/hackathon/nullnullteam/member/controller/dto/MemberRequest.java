package com.hackathon.nullnullteam.member.controller.dto;

import com.hackathon.nullnullteam.member.Gender;
import com.hackathon.nullnullteam.member.service.dto.MemberCommand;
import lombok.Builder;

public class MemberRequest {

    @Builder
    public record Info(
        String name,
        String gender,
        int age,
        String profile,
        String email
    ) {

        public MemberCommand.Info toCommand() {
            return MemberCommand.Info.builder()
                .name(name)
                .gender(gender)
                .age(age)
                .profile(profile)
                .email(email)
                .build();
        }
    }
}
