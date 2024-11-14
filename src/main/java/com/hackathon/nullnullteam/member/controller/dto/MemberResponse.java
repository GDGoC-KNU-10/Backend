package com.hackathon.nullnullteam.member.controller.dto;

import com.hackathon.nullnullteam.member.Member;
import com.hackathon.nullnullteam.member.service.dto.MemberModel;
import com.hackathon.nullnullteam.member.service.dto.MemberModel.Info;
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
}
