package com.hackathon.nullnullteam.vitamin.service.dto;

import com.hackathon.nullnullteam.vitamin.IntakeFrequency;
import com.hackathon.nullnullteam.vitamin.Vitamin;
import lombok.Builder;

import java.time.LocalDate;

public class VitaminModel {
    @Builder
    public record Info(
            Long vitaminId,
            String vitaminName,
            IntakeFrequency intakeFrequency,
            String oneTakeAmount,
            LocalDate createdAt
    ) {
        public static VitaminModel.Info from(Vitamin vitamin) {
            return Info.builder()
                    .vitaminId(vitamin.getId())
                    .vitaminName(vitamin.getVitaminName())
                    .intakeFrequency(vitamin.getIntakeFrequency())
                    .oneTakeAmount(vitamin.getOnetakeAmount())
                    .createdAt(vitamin.getCreatedAt().toLocalDate())
                    .build();
        }
    }
}