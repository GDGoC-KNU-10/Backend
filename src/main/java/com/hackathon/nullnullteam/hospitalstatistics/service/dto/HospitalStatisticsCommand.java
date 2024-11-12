package com.hackathon.nullnullteam.hospitalstatistics.service.dto;

import com.hackathon.nullnullteam.hospitalstatistics.HospitalStatistics;
import com.hackathon.nullnullteam.hospitalstatistics.ResultType;
import com.hackathon.nullnullteam.member.Member;
import lombok.Builder;

public class HospitalStatisticsCommand {
    @Builder
    public record Add(
            String name,
            Integer amount,
            ResultType result,
            String description
    ) {
        public HospitalStatistics toEntity(Member member) {
            return HospitalStatistics.builder()
                    .member(member)
                    .name(name)
                    .amount(amount)
                    .result(result)
                    .description(description)
                    .build();
        }
    }
}