package com.hackathon.nullnullteam.vitamin.controller.dto;

import com.hackathon.nullnullteam.vitamin.IntakeFrequency;
import com.hackathon.nullnullteam.vitamin.service.dto.VitaminModel;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class VitaminResponse {
    @Builder
    public record Info(
            Long vitaminId,
            String vitaminName,
            IntakeFrequency intakeFrequency,
            String oneTakeAmount,
            LocalDate createdAt
    ) {
        public static VitaminResponse.Info from(VitaminModel.Info model) {
            return Info.builder()
                    .vitaminId(model.vitaminId())
                    .vitaminName(model.vitaminName())
                    .intakeFrequency(model.intakeFrequency())
                    .oneTakeAmount(model.oneTakeAmount())
                    .createdAt(model.createdAt())
                    .build();
        }
    }

    @Builder
    public record Infos(
            Page<Info> infos
    ) {
        public static VitaminResponse.Infos from(Page<VitaminModel.Info> models) {
            return Infos.builder()
                    .infos(models.map(VitaminResponse.Info::from))
                    .build();
        }
    }

}
