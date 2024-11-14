package com.hackathon.nullnullteam.member.service.dto;

import com.hackathon.nullnullteam.member.Member;
import lombok.Builder;

public class MemberModel {

    @Builder
    public record Info(
        String name,
        String status,
        int age,
        String gender
    ) {

        public static MemberModel.Info from(Member member) {
            return Info.builder()
                .name(member.getName())
                .status(member.getAnxietyLevel().getDescription())
                .age(member.getAge())
                .gender(member.getGender().getDescription())
                .build();
        }
    }
    @Builder
    public record Login(
            String jwt
    ) {
        public static Login from(String jwt) {
            return Login.builder()
                    .jwt(jwt)
                    .build();
        }
    }
}
