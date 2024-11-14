package com.hackathon.nullnullteam.member.infrastructure.apicaller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UserInfoResponse(Long id, KakaoAccount kakaoAccount) {

}
