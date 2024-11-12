package com.hackathon.nullnullteam.hospitalstatistics.service.dto;

import com.hackathon.nullnullteam.hospitalstatistics.HospitalStatistics;
import com.hackathon.nullnullteam.hospitalstatistics.ResultType;
import lombok.Builder;

import java.time.LocalDate;

public class HospitalStatisticsModel {
    @Builder
    public record Info(
            Long hospitalStatisticsId,
            String name,
            Integer amount,
            ResultType result,
            String description,
            LocalDate createdAt
    ) {
        public static HospitalStatisticsModel.Info from(HospitalStatistics hospitalStatistics) {
            return Info.builder()
                    .hospitalStatisticsId(hospitalStatistics.getId())
                    .name(hospitalStatistics.getName())
                    .amount(hospitalStatistics.getAmount())
                    .result(hospitalStatistics.getResult())
                    .description(hospitalStatistics.getDescription())
                    .createdAt(hospitalStatistics.getCreatedAt().toLocalDate())
                    .build();
        }
    }
}