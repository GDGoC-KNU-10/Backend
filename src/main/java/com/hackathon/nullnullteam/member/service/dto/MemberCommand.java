package com.hackathon.nullnullteam.member.service.dto;

import lombok.Builder;

public class MemberCommand {

    @Builder
    public record Info(
        String name,
        String gender,
        int age,
        String profile,
        String email
    ) {


    }
}
