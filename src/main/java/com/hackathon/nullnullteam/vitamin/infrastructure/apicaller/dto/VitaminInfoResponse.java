package com.hackathon.nullnullteam.vitamin.infrastructure.apicaller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record VitaminInfoResponse(Header header, Body body) {

    public record Header(String resultCode, String resultMsg) {}

    public record Body(int pageNo, int totalCount, int numOfRows, List<ItemWrapper> items) {}

    // items 배열의 각 요소가 item 객체를 포함하는 구조를 표현
    public record ItemWrapper(Item item) {}

    public record Item(
//            @JsonProperty("ENTRPS") String entrps,
            @JsonProperty("PRDUCT") String prduct,
//            @JsonProperty("STTEMNT_NO") String sttemntNo,
//            @JsonProperty("REGIST_DT") String registDt,
//            @JsonProperty("DISTB_PD") String distbPd,
            @JsonProperty("SUNGSANG") String sungsang,
            @JsonProperty("SRV_USE") String srvUse
//            @J    sonProperty("PRSRV_PD") String prsrvPd,
//            @JsonProperty("INTAKE_HINT1") String intakeHint1,
//            @JsonProperty("MAIN_FNCTN") String mainFnctn,
//            @JsonProperty("BASE_STANDARD") String baseStandard
    ) {}
}