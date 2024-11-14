package com.hackathon.nullnullteam.member.service.dto;

import com.hackathon.nullnullteam.member.Member;
import lombok.Builder;

public class MemberModel {

    @Builder
    public record Info(
        String name,
        String status
    ) {

        public static MemberModel.Info from(Member member) {
            return Info.builder()
                .name(member.getName())
                .status(member.getAnxietyLevel().getDescription())
                .build();
        }
    }
}