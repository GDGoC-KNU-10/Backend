package com.hackathon.nullnullteam.vaccinationrecommend.controller.dto;

import com.hackathon.nullnullteam.vaccinationrecommend.VaccinationRecommend;
import lombok.Builder;

import java.util.List;

public class VaccinationRecommendResponse {
    @Builder
    public record Infos(
            List<VaccinationRecommend> recommends
    ){
        public static Infos from(List<VaccinationRecommend> list){
            return Infos.builder()
                    .recommends(list)
                    .build();
        }
    }
}
