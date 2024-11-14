package com.hackathon.nullnullteam.member.controller.dto;

import com.hackathon.nullnullteam.member.service.dto.MemberModel;
import lombok.Builder;

public class MemberResponse {

    @Builder
    public record Info(
        String name,
        String status
    ) {

        public static MemberResponse.Info from(MemberModel.Info model) {
            return Info.builder()
                .name(model.name())
                .status(model.status())
                .build();
        }
    }

    @Builder
    public record Login(
            String jwt
    ) {
        public static MemberResponse.Login from(MemberModel.Login model) {
            return MemberResponse.Login.builder()
                    .jwt(model.jwt())
                    .build();
        }
    }
}
