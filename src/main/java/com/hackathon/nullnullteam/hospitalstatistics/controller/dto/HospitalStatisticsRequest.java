package com.hackathon.nullnullteam.hospitalstatistics.controller.dto;

import com.hackathon.nullnullteam.hospitalstatistics.ResultType;
import com.hackathon.nullnullteam.hospitalstatistics.service.dto.HospitalStatisticsCommand;
import lombok.Builder;

public class HospitalStatisticsRequest {
    @Builder
    public record Add(
            String name,
            Integer amount,
            ResultType result,
            String description
    ) {
        public HospitalStatisticsCommand.Add toCommand() {
            return HospitalStatisticsCommand.Add.builder()
                    .name(name)
                    .amount(amount)
                    .result(result)
                    .description(description)
                    .build();
        }
    }
}