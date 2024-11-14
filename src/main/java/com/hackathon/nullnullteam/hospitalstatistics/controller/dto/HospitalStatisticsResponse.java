package com.hackathon.nullnullteam.hospitalstatistics.controller.dto;

import com.hackathon.nullnullteam.hospitalstatistics.ResultType;
import com.hackathon.nullnullteam.hospitalstatistics.service.dto.HospitalStatisticsModel;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class HospitalStatisticsResponse {
    @Builder
    public record Info(
            Long hospitalStatisticsId,
            String name,
            Integer amount,
            ResultType result,
            String description,
            LocalDate createdAt
    ) {
        public static HospitalStatisticsResponse.Info from(HospitalStatisticsModel.Info model) {
            return Info.builder()
                    .hospitalStatisticsId(model.hospitalStatisticsId())
                    .name(model.name())
                    .amount(model.amount())
                    .result(model.result())
                    .description(model.description())
                    .createdAt(model.createdAt())
                    .build();
        }
    }

    @Builder
    public record Infos(
            Page<Info> infos
    ) {
        public static HospitalStatisticsResponse.Infos from(Page<HospitalStatisticsModel.Info> models) {
            return Infos.builder()
                    .infos(models.map(HospitalStatisticsResponse.Info::from))
                    .build();
        }
    }
}